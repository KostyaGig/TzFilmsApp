package com.zinoview.tzfilmsapp.presentation.state

import com.zinoview.tzfilmsapp.core.Abstract
import com.zinoview.tzfilmsapp.core.BaseFilm

interface MapperUiToUIStateFilms : Abstract.FilmsMapper<List<UiStateFilm>> {

    class Base(
        private val mapperUiToUiStateFilm: MapperUiToUiStateFilm
    ) : MapperUiToUIStateFilms {

        override fun map(films: List<BaseFilm>): List<UiStateFilm>
            = films.map { uiFilm ->
                uiFilm.map(mapperUiToUiStateFilm)
            }

        override fun map(message: String): List<UiStateFilm>
            = listOf(UiStateFilm.Failure(message))
    }
}