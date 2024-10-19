package com.example.sheduleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheduleapp.databinding.FragmentTodayBinding

class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding must not be null")
    private lateinit var todayAdapter: SheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todayAdapter = SheduleAdapter(getTodayShedule())
        binding.recyclerViewToday.apply {
            adapter = todayAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getTodayShedule(): List<SheduleItem> {
        val day = mutableListOf<SheduleItem>()
        day.add(SheduleItem("12-14", "it", "326", "vera"))
        day.add(SheduleItem("12-14", "it", "326", "vera"))
        day.add(SheduleItem("12-14", "it", "326", "vera"))
        day.add(SheduleItem("12-14", "it", "326", "vera"))
        day.add(SheduleItem("12-14", "it", "326", "vera"))
        return day
    }
}