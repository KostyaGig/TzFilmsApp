package com.zinoview.tzfilmsapp.core

import android.app.Application
import com.zinoview.tzfilmsapp.presentation.di.component.AppComponent
import com.zinoview.tzfilmsapp.presentation.di.component.DaggerAppComponent
import com.zinoview.tzfilmsapp.presentation.di.module.AppModule

class FilmsApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

}