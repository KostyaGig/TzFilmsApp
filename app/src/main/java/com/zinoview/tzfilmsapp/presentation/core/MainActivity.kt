package com.zinoview.tzfilmsapp.presentation.core

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.zinoview.tzfilmsapp.R
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

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component().inject(this)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFrag.navController
        navController.setGraph(R.navigation.nav_graph, intent.extras)

        setupActionBarWithNavController(this,navController)
    }

    private fun Activity.component(): AppComponent {
        val application = application
        return if (application is FilmsApp) {
            application.component
        } else {
            throw IllegalArgumentException("Application $application is not UsersApp")
        }
    }

    override fun onBackPressed() {
        if(navController.graph.startDestination == navController.currentDestination?.id) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigate(R.id.filmsFragment)
        return true
    }
}