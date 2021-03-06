package com.zinoview.tzfilmsapp.presentation

import com.zinoview.tzfilmsapp.core.Abstract

interface MapperDomainToUiFilm : Abstract.FilmMapper<UiFilm> {

    class Base : MapperDomainToUiFilm {

        override fun map(
            id: Int,
            localizedName: String,
            name: String,
            year: Int,
            rating: Float,
            imageUrl: String,
            description: String,
            genres: List<String>
        ): UiFilm
            = UiFilm.Base(
                id, localizedName, name, year, rating, imageUrl, description, genres
            )

    }
}