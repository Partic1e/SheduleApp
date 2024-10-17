package com.example.sheduleapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sheduleapp.databinding.ItemSheduleBinding

class SheduleAdapter(private val sheduleList: List<SheduleItem>) :
    RecyclerView.Adapter<SheduleAdapter.SheduleViewHolder>() {

     inner class SheduleViewHolder(private val binding: ItemSheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sheduleItem: SheduleItem) {
            binding.textViewTime.text = sheduleItem.time
            binding.textViewName.text = sheduleItem.name
            binding.textViewLocation.text = sheduleItem.location
            binding.textViewTeacher.text = sheduleItem.teacher
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SheduleViewHolder {
        val binding = ItemSheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SheduleViewHolder, position: Int) {
        holder.bind(sheduleList[position])
    }

    override fun getItemCount(): Int = sheduleList.size
}




