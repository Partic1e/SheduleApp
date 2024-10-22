package com.example.sheduleapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shedule_items")
data class ScheduleItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val time: String,
    val name: String,
    val location: String,
    val teacher: String,
    val dayOfWeek: Int,
    val weekNumber: Int
)
