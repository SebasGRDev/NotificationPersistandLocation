package com.sebasgrdev.ejerciciocomplementario

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.google.firebase.messaging.messaging

class MyApp: Application() {

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "NotificationChannelFCM"
    }

    override fun onCreate() {
        super.onCreate()
        Firebase.messaging.token.addOnCompleteListener {
            if (!it.isSuccessful) {
                println("Token no generado")
                return@addOnCompleteListener
            }
            val token = it.result
            println("TOKEN: $token")
        }
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notificaciones FCM",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Notificaciones recibidas desde Firebase Cloud Messaging"
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}