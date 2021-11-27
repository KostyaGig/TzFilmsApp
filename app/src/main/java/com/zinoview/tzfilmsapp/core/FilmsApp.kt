package com.zinoview.tzfilmsapp.core

import android.app.Application
import com.zinoview.tzfilmsapp.presentation.di.component.AppComponent
import com.zinoview.tzfilmsapp.presentation.di.component.DaggerAppComponent
import com.zinoview.tzfilmsapp.presentation.di.module.AppModule
import com.zinoview.tzfilmsapp.presentation.presenter.FilmsPresenter
import javax.inject.Inject

class FilmsApp : Application() {

    lateinit var component: AppComponent

    @Inject
    lateinit var filmsPresenter: FilmsPresenter

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

}