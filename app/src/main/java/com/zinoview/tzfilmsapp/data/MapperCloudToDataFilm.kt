package com.zinoview.tzfilmsapp.data

import com.zinoview.tzfilmsapp.core.Abstract

interface MapperCloudToDataFilm : Abstract.FilmMapper<DataFilm> {

    class Base : MapperCloudToDataFilm {

        override fun map(
            id: Int,
            localized_name: String,
            name: String,
            year: Int,
            rating: Float,
            imageUrl: String,
            description: String,
            genres: List<String>
        ): DataFilm
            = DataFilm.Base(
                id, localized_name, name, year, rating, imageUrl, description, genres
            )

    }
}