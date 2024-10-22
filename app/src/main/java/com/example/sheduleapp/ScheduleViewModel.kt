package com.example.sheduleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleViewModel(private val scheduleDao: ScheduleDao) : ViewModel() {

    fun getTodaySchedule(dayOfWeek: Int, weekNumber: Int, onResult: (List<ScheduleItem>) -> Unit) {
        viewModelScope.launch {
            val schedule = scheduleDao.getScheduleForDay(dayOfWeek, weekNumber)
            onResult(schedule)
        }
    }

    fun getWeekSchedule(weekNumber: Int, onResult: (List<Any>) -> Unit) {
        viewModelScope.launch {
            val schedule = mutableListOf<Any>()
            val daysOfWeek = listOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота")

            for (dayIndex in 1..6) {
                schedule.add(daysOfWeek[dayIndex - 1])
                val scheduleItems = withContext(Dispatchers.IO) {
                    scheduleDao.getScheduleForDay(dayIndex, weekNumber)
                }
                schedule.addAll(scheduleItems)
            }
            onResult(schedule)
        }
    }
}
