package com.zinoview.tzfilmsapp.presentation

import com.zinoview.tzfilmsapp.R
import com.zinoview.tzfilmsapp.core.ResourceProvider
import com.zinoview.tzfilmsapp.presentation.adapter.FilmsAdapter
import com.zinoview.tzfilmsapp.presentation.core.log
import com.zinoview.tzfilmsapp.presentation.presenter.view.FilmsView
import com.zinoview.tzfilmsapp.presentation.state.UiStateFilm

interface FilmsWithCategories {

    fun create(films: List<UiStateFilm>)

    fun create(genre: String)

    fun update(view: FilmsView)

    fun clear()

    fun films(): List<UiStateFilm>


    class Base(
        private val resourceProvider: ResourceProvider
    ) : FilmsWithCategories {

        private val allFilms = ArrayList<UiStateFilm>()
        private val uiStateFilm = ArrayList<UiStateFilm>()

        override fun create(films: List<UiStateFilm>) {
            val first = films.first()
            if (first is UiStateFilm.Base) {
                addGeneres()
                allFilms.addAll(films)
                uiStateFilm.addAll(films)
            } else {
                uiStateFilm.add(first)
            }
        }

        override fun create(genre: String) {
            val copyAllFilms = ArrayList(allFilms)
            log("create all films size ${copyAllFilms.size}")
            val matchesGenreFilms = copyAllFilms.filter { film ->
                film.match(genre)
            }

            uiStateFilm.clear()
            addGeneres()
            uiStateFilm.forEach {film ->
                film.select(genre)
            }

            uiStateFilm.addAll(matchesGenreFilms)
        }

        private fun addGeneres() {
            uiStateFilm.add(UiStateFilm.Category(resourceProvider.string(R.string.genre_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.phantezy_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.crime_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.detective_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.melodrama_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.bio_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.comedy_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.fantastic_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.boevik_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.triller_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.musicl_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.adventures_text)))
            uiStateFilm.add(UiStateFilm.Genre(resourceProvider.string(R.string.horror_text)))
            uiStateFilm.add(UiStateFilm.Category(resourceProvider.string(R.string.films_text)))
        }

        override fun update(view: FilmsView) {
            view.updateState(uiStateFilm)
        }

        override fun films(): List<UiStateFilm>
            = uiStateFilm

        override fun clear() {
            this.uiStateFilm.clear()
            this.allFilms.clear()
        }
    }
}