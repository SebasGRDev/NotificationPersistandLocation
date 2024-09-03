package com.sebasgrdev.ejerciciocomplementario

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotificationEntity::class], version = 1)
abstract class AppDatabaseRoom: RoomDatabase() {
    abstract fun notificationsDao() : NotificationsDao
}