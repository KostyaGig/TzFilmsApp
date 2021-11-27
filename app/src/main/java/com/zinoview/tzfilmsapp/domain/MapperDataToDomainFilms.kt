package com.zinoview.tzfilmsapp.domain

import com.zinoview.tzfilmsapp.core.Abstract
import com.zinoview.tzfilmsapp.core.BaseFilm

interface MapperDataToDomainFilms : Abstract.FilmsMapper<DomainFilms> {

    class Base(
        private val mapperDataToDomainFilm: MapperDataToDomainFilm
    ) : MapperDataToDomainFilms {

        override fun map(films: List<BaseFilm>): DomainFilms
            = DomainFilms.Success(
                map(films,mapperDataToDomainFilm)
            )

        override fun map(message: String): DomainFilms
            = DomainFilms.Failure(message)
    }
}