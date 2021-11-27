package com.zinoview.tzfilmsapp.data.cloud

import com.google.gson.annotations.SerializedName

interface CloudFilms {

    fun films() : List<CloudFilm>

    class Base(
        @SerializedName("films")
        private val films: List<CloudFilm.Base>
    ) : CloudFilms {

        override fun films(): List<CloudFilm>
            = films
    }
}