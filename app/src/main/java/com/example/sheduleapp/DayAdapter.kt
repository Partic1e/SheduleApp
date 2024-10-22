package com.example.sheduleapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduleapp.databinding.ItemSheduleBinding

class DayAdapter(private var scheduleList: List<ScheduleItem>) :
    RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

     inner class DayViewHolder(private val binding: ItemSheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(scheduleItem: ScheduleItem) {
            binding.time.text = scheduleItem.time
            binding.name.text = scheduleItem.name
            binding.location.text = scheduleItem.location
            binding.teacher.text = scheduleItem.teacher
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val binding = ItemSheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(scheduleList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newScheduleList: List<ScheduleItem>) {
        scheduleList = newScheduleList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = scheduleList.size
}
