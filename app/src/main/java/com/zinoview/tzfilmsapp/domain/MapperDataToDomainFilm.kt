package com.zinoview.tzfilmsapp.domain

import com.zinoview.tzfilmsapp.core.Abstract

interface MapperDataToDomainFilm : Abstract.FilmMapper<DomainFilm> {

    class Base : MapperDataToDomainFilm {

        override fun map(
            id: Int,
            localizedName: String,
            name: String,
            year: Int,
            rating: Float,
            imageUrl: String,
            description: String,
            genres: List<String>
        ): DomainFilm
            = DomainFilm.Base(
                id, localizedName, name, year, rating, imageUrl, description, genres
            )

    }
}