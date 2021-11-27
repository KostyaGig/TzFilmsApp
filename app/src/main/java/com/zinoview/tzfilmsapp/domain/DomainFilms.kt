package com.zinoview.tzfilmsapp.domain

import android.os.Message
import com.zinoview.tzfilmsapp.core.Abstract
import com.zinoview.tzfilmsapp.core.BaseFilm

sealed class DomainFilms : Abstract.Films {

    class Success(
        private val films: List<BaseFilm>
    ) : DomainFilms() {

        override fun <T> map(mapper: Abstract.FilmsMapper<T>): T
            = mapper.map(films)
    }

    class Failure(
        private val message: String
    ) : DomainFilms() {

        override fun <T> map(mapper: Abstract.FilmsMapper<T>): T
            = mapper.map(message)
    }
}
