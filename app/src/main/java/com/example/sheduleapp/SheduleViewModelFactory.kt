package com.example.sheduleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SheduleViewModelFactory(private val sheduleDao: SheduleDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SheduleViewModel(sheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
