package com.example.sheduleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sheduleapp.databinding.FragmentRootBinding

class RootFragment : Fragment() {
    private lateinit var binding: FragmentRootBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRootBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, TodayFragment())
            .commit()

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.today_item -> {
                    childFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, TodayFragment())
                        .commit()
                    true
                }
                R.id.week_item -> {
                    childFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainer.id, WeekFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
