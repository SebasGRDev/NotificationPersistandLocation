package com.sebasgrdev.ejerciciocomplementario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotificationsViewModel(private val notificationsDao: NotificationsDao) : ViewModel() {

    private val _notifications = MutableStateFlow<List<NotificationEntity>>(emptyList())
    val notifications: StateFlow<List<NotificationEntity>> = _notifications.asStateFlow()

    init {
        viewModelScope.launch {
            notificationsDao.getAllNotifications().collect {
                _notifications.value= it
            }
        }
    }

}

class NotificationsViewModelFactory(private val notificationsDao: NotificationsDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationsViewModel::class.java)) {
            return NotificationsViewModel(notificationsDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}