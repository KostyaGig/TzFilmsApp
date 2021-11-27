package com.zinoview.tzfilmsapp.presentation.di.component

import com.zinoview.tzfilmsapp.presentation.core.MainActivity
import com.zinoview.tzfilmsapp.presentation.di.module.AppModule
import com.zinoview.tzfilmsapp.presentation.fragment.FilmsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: FilmsFragment)
}