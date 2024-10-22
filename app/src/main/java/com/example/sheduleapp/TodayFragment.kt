package com.example.sheduleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheduleapp.databinding.FragmentTodayBinding
import java.util.Calendar

class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding must not be null")

    private var _todayAdapter: DayAdapter? = null
    private val todayAdapter
        get() = _todayAdapter ?: throw IllegalStateException()

    private val scheduleViewModel: ScheduleViewModel by viewModels {
        ScheduleViewModelFactory(AppDatabase.getDatabase(requireContext()).sheduleDao())
    }

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

        _todayAdapter = DayAdapter(listOf())
        binding.recyclerViewToday.apply {
            adapter = todayAdapter
            layoutManager = LinearLayoutManager(context)
        }

        scheduleViewModel.getTodaySchedule(getDayOfWeek(), isEvenWeek()) { sheduleItems ->
            todayAdapter.updateData(sheduleItems)
        }
    }

    private fun getDayOfWeek(): Int {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        return if (day == Calendar.SUNDAY) 1 else day - 1
    }

    private fun isEvenWeek(): Int {
        val calendar = Calendar.getInstance()
        val weekNumber = calendar.get(Calendar.WEEK_OF_YEAR)
        return if (weekNumber % 2 == 0) 1 else 2
    }
}
