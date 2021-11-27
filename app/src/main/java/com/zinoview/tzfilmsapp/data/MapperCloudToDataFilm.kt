package com.zinoview.tzfilmsapp.data

import com.zinoview.tzfilmsapp.core.Abstract

interface MapperCloudToDataFilm : Abstract.FilmMapper<DataFilm> {

    class Base : MapperCloudToDataFilm {

        override fun map(
            id: Int,
            localizedName: String,
            name: String,
            year: Int,
            rating: Float,
            imageUrl: String,
            description: String,
            genres: List<String>
        ): DataFilm
            = DataFilm.Base(
                id, localizedName, name, year, rating, imageUrl, description, genres
            )

    }
}