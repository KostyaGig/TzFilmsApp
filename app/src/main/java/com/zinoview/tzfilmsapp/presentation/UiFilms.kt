package com.zinoview.tzfilmsapp.presentation

import android.os.Message
import com.zinoview.tzfilmsapp.core.Abstract
import com.zinoview.tzfilmsapp.core.BaseFilm

sealed class UiFilms : Abstract.Films {

    class Success(
        private val films: List<BaseFilm>
    ) : UiFilms() {

        override fun <T> map(mapper: Abstract.FilmsMapper<T>): T
            = mapper.map(films)
    }

    class Failure(
        private val message: String
    ) : UiFilms() {

        override fun <T> map(mapper: Abstract.FilmsMapper<T>): T
            = mapper.map(message)
    }
}
