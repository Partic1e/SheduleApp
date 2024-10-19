package com.example.sheduleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SheduleViewModel(private val sheduleDao: SheduleDao) : ViewModel() {

    fun getTodayShedule(dayOfWeek: Int, weekNumber: Int, onResult: (List<SheduleItem>) -> Unit) {
        viewModelScope.launch {
            val shedule = sheduleDao.getSheduleForDay(dayOfWeek, weekNumber)
            onResult(shedule)
        }
    }
}
