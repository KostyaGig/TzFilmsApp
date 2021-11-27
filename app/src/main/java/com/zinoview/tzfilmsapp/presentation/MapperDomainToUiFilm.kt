package com.zinoview.tzfilmsapp.presentation

import com.zinoview.tzfilmsapp.core.Abstract

interface MapperDomainToUiFilm : Abstract.FilmMapper<UiFilm> {

    class Base : MapperDomainToUiFilm {

        override fun map(
            id: Int,
            localized_name: String,
            name: String,
            year: Int,
            rating: Float,
            imageUrl: String,
            description: String,
            genres: List<String>
        ): UiFilm
            = UiFilm.Base(
                id, localized_name, name, year, rating, imageUrl, description, genres
            )

    }
}