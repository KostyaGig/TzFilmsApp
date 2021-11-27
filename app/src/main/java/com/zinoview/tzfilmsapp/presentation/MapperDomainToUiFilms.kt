package com.zinoview.tzfilmsapp.presentation

import com.zinoview.tzfilmsapp.core.Abstract
import com.zinoview.tzfilmsapp.core.BaseFilm

interface MapperDomainToUiFilms : Abstract.FilmsMapper<UiFilms> {

    class Base(
        private val mapperDomainToUiFilm: MapperDomainToUiFilm
    ) : MapperDomainToUiFilms {

        override fun map(films: List<BaseFilm>): UiFilms
            = UiFilms.Success(
                map(films,mapperDomainToUiFilm)
            )

        override fun map(message: String): UiFilms
            = UiFilms.Failure(message)
    }
}