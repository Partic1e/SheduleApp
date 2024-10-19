package com.example.sheduleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheduleapp.databinding.FragmentWeekBinding

class WeekFragment : Fragment() {

    private var _binding : FragmentWeekBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding must not be null")
    private var _weekAdapter: SheduleAdapter? = null
    private val weekAdapter
        get() = _weekAdapter ?: throw IllegalStateException()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _weekAdapter = SheduleAdapter(getWeekShedule())
        binding.recyclerViewWeek.apply {
            adapter = weekAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getWeekShedule(): List<SheduleItem> {
        val day = mutableListOf<SheduleItem>()
        day.add(SheduleItem("15-14", "it", "326", "vera"))
        day.add(SheduleItem("12-14", "it", "326", "vera"))
        day.add(SheduleItem("12-14", "it", "326", "vera"))
        day.add(SheduleItem("12-14", "it", "326", "vera"))
        day.add(SheduleItem("12-14", "it", "326", "vera"))
        return day
    }
}