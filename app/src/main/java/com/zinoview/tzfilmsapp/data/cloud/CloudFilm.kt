package com.zinoview.tzfilmsapp.data.cloud

import com.google.gson.annotations.SerializedName
import com.zinoview.tzfilmsapp.core.Abstract
import com.zinoview.tzfilmsapp.core.BaseFilm

interface CloudFilm : BaseFilm {

    class Base(
        @SerializedName("id")
        private val id: Int,
        @SerializedName("localized_name")
        private val localized_name: String,
        @SerializedName("name")
        private val name: String,
        @SerializedName("year")
        private val year: Int,
        @SerializedName("rating")
        private val rating: Float,
        @SerializedName("image_url")
        private val imageUrl: String = "",
        @SerializedName("description")
        private val description: String? = "",
        @SerializedName("genres")
        private val genres: List<String>
    ) : CloudFilm {

        override fun <T> map(mapper: Abstract.FilmMapper<T>): T
            = mapper.map(
                id, localized_name, name, year, rating, imageUrl.notNullImageUrl(), description.notNull(), genres
            )

        private fun String?.notNull() : String {
            return this ?: ""
        }

        private fun String?.notNullImageUrl() : String {
            return this ?: EMPTY_IMAGE_URL
        }

        private companion object {
            private const val EMPTY_IMAGE_URL = "https://samogon-cheb.ru/data/images/system/empty-img.png"
        }
    }
}