package com.sebasgrdev.ejerciciocomplementario

import android.os.Bundle
import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabaseRoom
    private lateinit var notificationsDao: NotificationsDao
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter
    val viewModel: NotificationsViewModel by viewModels {
        NotificationsViewModelFactory(notificationsDao)
    }

    private val locationManager: LocationService = LocationService()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Log.i("Permission:", "Granted")
                Toast.makeText(this, getString(R.string.permission_granted), Toast.LENGTH_SHORT)
                    .show()
            } else {
                Log.i("Permission:", "Denied")
                showPermissionDeniedSnackbar()
            }
        }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabaseRoom::class.java,
            "mibasededatos"
        ).build()

        notificationsDao = db.notificationsDao()

        recyclerView = findViewById(R.id.recyclerViewContainer)
        adapter = NotificationAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observa los datos del ViewModel
        viewModel.notifications.asLiveData().observe(this) { notifications ->
            adapter.submitList(notifications)
        }


        requestLocationPermission()

        val tvLocationLatitude = findViewById<TextView>(R.id.tvLocationLatitude)
        val tvLocationLongitude = findViewById<TextView>(R.id.tvLocationLongitude)
        val btnLocation = findViewById<Button>(R.id.btnLocation)

        btnLocation.setOnClickListener {
            lifecycleScope.launch {
                val result = locationManager.getUserLocation(this@MainActivity)
                if (result != null) {
                    tvLocationLatitude.text = "Latitude: ${result.latitude}"
                    tvLocationLongitude.text = "Longitude: ${result.longitude}"
                } else {
                    Toast.makeText(this@MainActivity, getString(R.string.location_enable_required), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showPermissionDeniedSnackbar() {
        val view: View = findViewById(R.id.main)
        val snackbar = Snackbar.make(
            view,
            getString(R.string.permission_required),
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction(getString(R.string.ok)) {
            closeContextMenu()
        }
        snackbar.show()
    }

    private fun requestLocationPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}