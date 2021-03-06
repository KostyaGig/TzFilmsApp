package com.zinoview.tzfilmsapp.presentation.state

import com.zinoview.tzfilmsapp.core.Abstract
import com.zinoview.tzfilmsapp.presentation.StringLengthMapper

interface MapperUiToUiStateFilm : Abstract.FilmMapper<UiStateFilm> {

    class Base(
        private val stringLengthMapper: StringLengthMapper
    ) : MapperUiToUiStateFilm {

        override fun map(
            id: Int,
            localizedName: String,
            name: String,
            year: Int,
            rating: Float,
            imageUrl: String,
            description: String,
            genres: List<String>
        ): UiStateFilm
            = UiStateFilm.Base(
                id, localizedName, name,stringLengthMapper.map(name), year, rating, imageUrl, description, genres
            )

    }
}