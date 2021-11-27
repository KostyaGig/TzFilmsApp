package com.zinoview.tzfilmsapp.presentation.presenter.view

import com.zinoview.tzfilmsapp.presentation.state.UiStateFilm

interface FilmsView {

    fun updateState(films: List<UiStateFilm>)

    object Empty : FilmsView {

        override fun updateState(films: List<UiStateFilm>)
            = Unit
    }
}