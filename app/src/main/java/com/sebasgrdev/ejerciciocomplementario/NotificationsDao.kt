package com.sebasgrdev.ejerciciocomplementario

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.sebasgrdev.ejerciciocomplementario.NotificationEntity
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NotificationsDao {
    @Insert
    suspend fun insertNotification(notification: NotificationEntity)

    @Query("SELECT * FROM mibasededatos")
    fun getAllNotifications(): Flow<List<NotificationEntity>>
}