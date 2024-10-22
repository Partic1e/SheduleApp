package com.example.sheduleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sheduleapp.databinding.ActivityMainBinding
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appDatabase = AppDatabase.getDatabase(this)
        initializeDatabase(this, appDatabase.sheduleDao())

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, RootFragment())
                .commit()
        }
    }

    private fun initializeDatabase(context: Context, sd: ScheduleDao) {
        CoroutineScope(Dispatchers.IO).launch {
            if (sd.getScheduleForDay(1, 1).isEmpty()) {
                val initialData = loadData(context)
                sd.insertAll(initialData)
            }
        }
    }

    private fun loadData(context: Context): List<ScheduleItem> {
        val inputStream = context.assets.open("initial_data.json")
        val bufferedReader = BufferedReader(inputStream.reader())
        val jsonString = bufferedReader.use { it.readText() }

        val listType = object : TypeToken<List<ScheduleItem>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}
