package com.zinoview.tzfilmsapp.presentation

import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.squareup.picasso.Picasso
import kotlinx.android.parcel.Parcelize

interface ClickedFilm : Parcelable {

    fun fillUi(
        filmImage: ImageView,
        filmName: TextView,
        filmYear: TextView,
        filmRate: TextView,
        filmDescription: TextView
    )

    fun handleToolbarTitle(toolbar: ActionBar)

    @Parcelize
    class Base(
        private val localizedName: String,
        private val name: String,
        private val year: Int,
        private val rating: Float,
        private val imageUrl: String,
        private val description: String
    ) : ClickedFilm {

        override fun fillUi(
            filmImage: ImageView,
            filmName: TextView,
            filmYear: TextView,
            filmRate: TextView,
            filmDescription: TextView
        ) {
            Picasso.get().load(imageUrl).into(filmImage)
            filmName.text = name
            filmYear.text = YEAR + year
            filmRate.text = RATING + rating
            filmDescription.text = description
        }

        private companion object {
            private const val YEAR = "Год: "
            private const val RATING = "Рейтинг: "
        }

        override fun handleToolbarTitle(toolbar: ActionBar) {
            toolbar.title = localizedName
        }

    }
}