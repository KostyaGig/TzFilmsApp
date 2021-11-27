package com.zinoview.tzfilmsapp.data

import com.zinoview.tzfilmsapp.core.Abstract
import com.zinoview.tzfilmsapp.core.BaseFilm

sealed class DataFilms : Abstract.Films {

    data class Success(
        private val films: List<BaseFilm>
    ) : DataFilms() {

        override fun <T> map(mapper: Abstract.FilmsMapper<T>): T
            = mapper.map(films)
    }

    data class Failure(
        private val message: String
    ) : DataFilms() {

        override fun <T> map(mapper: Abstract.FilmsMapper<T>): T
            = mapper.map(message)
    }
}
