package com.example.sheduleapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM shedule_items WHERE dayOfWeek = :dayOfWeek AND weekNumber = :weekNumber")
    suspend fun getScheduleForDay(dayOfWeek: Int, weekNumber: Int): List<ScheduleItem>

    @Insert
    suspend fun insertAll(items: List<ScheduleItem>)
}
