package com.sebasgrdev.ejerciciocomplementario.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sebasgrdev.ejerciciocomplementario.AppDatabaseRoom
import com.sebasgrdev.ejerciciocomplementario.NotificationAdapter
import com.sebasgrdev.ejerciciocomplementario.NotificationsDao
import com.sebasgrdev.ejerciciocomplementario.NotificationsViewModel
import com.sebasgrdev.ejerciciocomplementario.NotificationsViewModelFactory
import com.sebasgrdev.ejerciciocomplementario.R

class NotificationFragment : Fragment() {

    private lateinit var db: AppDatabaseRoom
    private lateinit var notificationsDao: NotificationsDao

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter

    private val notificationsViewModel: NotificationsViewModel by viewModels {
        NotificationsViewModelFactory(notificationsDao)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeDatabase()
        setRecyclerView(view)

    }

    private fun initializeDatabase() {
        db = Room.databaseBuilder(
            requireContext().applicationContext,
            AppDatabaseRoom::class.java,
            "mibasededatos"
        ).build()

        notificationsDao = db.notificationsDao()

        // Observa los datos del ViewModel :)
        notificationsViewModel.notifications.asLiveData().observe(viewLifecycleOwner) { notifications ->
            adapter.submitList(notifications)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    private fun setRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewContainer)
        adapter = NotificationAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }

}