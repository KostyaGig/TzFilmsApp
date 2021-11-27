package com.zinoview.tzfilmsapp.core

interface BaseFilm : Abstract.Film {

    data class Test(
        private val id: Int,
        private val name: String,
        private val imageUrl: String
    ) : BaseFilm {

        override fun <T> map(mapper: Abstract.FilmMapper<T>): T
            = mapper.map(id, "",name,-1,4.0f,imageUrl,"", emptyList())
    }
}