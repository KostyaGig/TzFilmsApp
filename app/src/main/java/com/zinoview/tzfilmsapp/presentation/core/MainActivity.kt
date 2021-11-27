package com.zinoview.tzfilmsapp.presentation.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zinoview.tzfilmsapp.core.FilmsApp
import com.zinoview.tzfilmsapp.databinding.ActivityMainBinding
import com.zinoview.tzfilmsapp.presentation.di.component.AppComponent


fun Any?.log(message: String) {
    Log.d("zinoviewk",message)
}

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding by lazy {
        checkNotNull(_binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component().inject(this)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        setSupportActionBar(binding.toolbar)
    }

    private fun Activity.component(): AppComponent {
        val application = application
        return if (application is FilmsApp) {
            application.component
        } else {
            throw IllegalArgumentException("Application $application is not UsersApp")
        }
    }
}