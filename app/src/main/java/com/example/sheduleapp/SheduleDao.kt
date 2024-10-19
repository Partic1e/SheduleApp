package com.example.sheduleapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SheduleDao {

    @Query("SELECT * FROM shedule_items WHERE dayOfWeek = :dayOfWeek AND weekNumber = :weekNumber")
    suspend fun getSheduleForDay(dayOfWeek: Int, weekNumber: Int): List<SheduleItem>

    @Insert
    suspend fun insertSheduleItem(item: SheduleItem)

    @Insert
    suspend fun insertAll(items: List<SheduleItem>)
}
