package com.zinoview.tzfilmsapp.core

interface  Abstract {

    interface Film {

        fun <T> map(mapper: FilmMapper<T>) : T
    }

    interface FilmMapper<T> : Mapper {

        fun map(
            id: Int,
            localized_name: String,
            name: String,
            year: Int,
            rating: Float,
            imageUrl: String,
            description: String,
            genres: List<String>
        ) : T
    }

    interface Films {

        fun <T> map(mapper: FilmsMapper<T>) : T
    }

    interface FilmsMapper<T> : Mapper {

        fun map(films: List<BaseFilm>) : T

        fun map(message: String) : T

        fun <T : BaseFilm> map(srcFilms: List<BaseFilm>,mapper: FilmMapper<T>) : List<BaseFilm> {
            return srcFilms.map { film ->
                film.map(mapper)
            }
        }
    }

    interface Mapper
}