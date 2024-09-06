package com.sebasgrdev.ejerciciocomplementario.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkBuilder
import com.google.android.material.textfield.TextInputEditText
import com.sebasgrdev.ejerciciocomplementario.MainActivity
import com.sebasgrdev.ejerciciocomplementario.MyApp
import com.sebasgrdev.ejerciciocomplementario.R

class SendNotificationFragment : Fragment(R.layout.fragment_send_notification) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_notification, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleNotification = view.findViewById<TextInputEditText>(R.id.etTitleSendNotification)
        val bodyNotification = view.findViewById<TextInputEditText>(R.id.etBodySendNotification)
        val btnSendNotification = view.findViewById<Button>(R.id.btnSendLocalNotification)

        createChannel()

        btnSendNotification.setOnClickListener {
            val title = titleNotification.text.toString()
            val body = bodyNotification.text.toString()
            createLocalNotification(title, body)
        }

    }

    fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MyApp.NOTIFICATION_CHANNEL_ID,
                "NotificationChannelLocal",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notificación Local"
            }

            val notificationManager:NotificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun createLocalNotification(title:String, body:String) {

        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            putExtra("fragment_id", R.id.notificationNav)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notificationManager =
            requireActivity().getSystemService(NotificationManager::class.java)
        var builder = NotificationCompat.Builder(requireContext(), MyApp.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification_logo)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(1, builder)

    }
}