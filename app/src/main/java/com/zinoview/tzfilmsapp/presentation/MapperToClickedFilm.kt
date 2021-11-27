package com.zinoview.tzfilmsapp.presentation

import com.zinoview.tzfilmsapp.core.Abstract

interface MapperToClickedFilm : Abstract.FilmMapper<ClickedFilm> {

    class Base : MapperToClickedFilm {

        override fun map(
            id: Int,
            localizedName: String,
            name: String,
            year: Int,
            rating: Float,
            imageUrl: String,
            description: String,
            genres: List<String>
        ): ClickedFilm
            = ClickedFilm.Base(
                localizedName,name,year,rating,imageUrl,description
            )
    }
}