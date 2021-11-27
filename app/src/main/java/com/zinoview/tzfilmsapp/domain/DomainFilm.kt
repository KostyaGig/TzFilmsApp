package com.zinoview.tzfilmsapp.domain

import com.zinoview.tzfilmsapp.core.Abstract
import com.zinoview.tzfilmsapp.core.BaseFilm

interface DomainFilm : BaseFilm {

    class Base(
        private val id: Int,
        private val localized_name: String,
        private val name: String,
        private val year: Int,
        private val rating: Float,
        private val imageUrl: String,
        private val description: String,
        private val genres: List<String>
    ) : DomainFilm {

        override fun <T> map(mapper: Abstract.FilmMapper<T>): T
            = mapper.map(id, localized_name, name, year, rating, imageUrl, description, genres)

    }
}