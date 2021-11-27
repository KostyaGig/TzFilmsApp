package com.zinoview.tzfilmsapp.presentation

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzfilmsapp.presentation.state.UiStateFilm

interface LayoutManager {

    fun layoutManager(context: Context,uiStateFilm: UiStateFilm) : RecyclerView.LayoutManager

    class Base : LayoutManager {

        override fun layoutManager(context: Context,uiStateFilm: UiStateFilm) : RecyclerView.LayoutManager {
            return if (
                uiStateFilm is UiStateFilm.Progress ||
                uiStateFilm is UiStateFilm.Failure) {
                LinearLayoutManager(context)
            } else {
                GridLayoutManager(context, SPAN_COUNT)
            }
        }

        private companion object {
            private const val SPAN_COUNT = 2
        }
    }

}