package com.example.sheduleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheduleapp.databinding.FragmentWeekBinding
import java.util.Calendar

class WeekFragment : Fragment() {

    private var _binding : FragmentWeekBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding must not be null")

    private var _weekAdapter: WeekAdapter? = null
    private val weekAdapter
        get() = _weekAdapter ?: throw IllegalStateException()

    private val scheduleViewModel: ScheduleViewModel by viewModels {
        ScheduleViewModelFactory(AppDatabase.getDatabase(requireContext()).sheduleDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _weekAdapter = WeekAdapter(listOf())
        binding.recyclerViewWeek.apply {
            adapter = weekAdapter
            layoutManager = LinearLayoutManager(context)
        }

        scheduleViewModel.getWeekSchedule(getWeekNumber()) { schedule ->
            weekAdapter.updateData(schedule)
        }
    }

    private fun getWeekNumber(): Int {
        val calendar = Calendar.getInstance()
        val weekNumber = calendar.get(Calendar.WEEK_OF_YEAR)
        return  if (weekNumber % 2 == 0) 1 else 2
    }
}
