package com.zinoview.tzfilmsapp.presentation.core

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.zinoview.tzfilmsapp.core.FilmsApp
import com.zinoview.tzfilmsapp.presentation.fragment.FilmsFragment

abstract class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {

    private val application by lazy {
        (requireActivity().application) as FilmsApp
    }

    protected val toolbar by lazy {
        checkNotNull((requireActivity() as MainActivity).supportActionBar)
    }

    protected fun inject(fragment: BaseFragment) {
        when(fragment) {
            is FilmsFragment -> application.component.inject(fragment)
        }
    }
}