package com.sebasgrdev.ejerciciocomplementario

import android.os.Bundle
import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sebasgrdev.ejerciciocomplementario.fragments.LocationFragment
import com.sebasgrdev.ejerciciocomplementario.fragments.NotificationFragment
import com.sebasgrdev.ejerciciocomplementario.fragments.SendNotificationFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavigationView = findViewById(R.id.bottomNavigationBar)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        if (intent.hasExtra("fragment_id")) {
            val fragmentId = intent.getIntExtra("fragment_id", 0)
            bottomNavigationView.selectedItemId = fragmentId
        }

        requestLocationPermission()

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
}