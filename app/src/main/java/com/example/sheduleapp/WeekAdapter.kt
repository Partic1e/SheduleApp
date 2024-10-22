package com.example.sheduleapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduleapp.databinding.DayHeaderBinding
import com.example.sheduleapp.databinding.ItemSheduleBinding

class WeekAdapter(private var items: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is String -> TYPE_HEADER
            is ScheduleItem -> TYPE_ITEM
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = DayHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DayHeaderViewHolder(binding)
            }
            TYPE_ITEM -> {
                val binding = ItemSheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ScheduleItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DayHeaderViewHolder -> holder.bind(items[position] as String)
            is ScheduleItemViewHolder -> holder.bind(items[position] as ScheduleItem)
        }
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<Any>) {
        items = newItems
        notifyDataSetChanged()
    }

    class DayHeaderViewHolder(private val binding: DayHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dayName: String) {
            binding.dayHeader.text = dayName
        }
    }

    class ScheduleItemViewHolder(private val binding: ItemSheduleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(scheduleItem: ScheduleItem) {
            binding.time.text = scheduleItem.time
            binding.name.text = scheduleItem.name
            binding.location.text = scheduleItem.location
            binding.teacher.text = scheduleItem.teacher
        }
    }
}
