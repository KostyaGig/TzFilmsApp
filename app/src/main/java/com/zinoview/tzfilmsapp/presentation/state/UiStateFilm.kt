package com.zinoview.tzfilmsapp.presentation.state

import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zinoview.tzfilmsapp.core.Abstract
import com.zinoview.tzfilmsapp.presentation.ClickedFilm
import com.zinoview.tzfilmsapp.presentation.MapperToClickedFilm
import com.zinoview.tzfilmsapp.presentation.adapter.Bind
import com.zinoview.tzfilmsapp.presentation.adapter.OnItemClickListener
import com.zinoview.tzfilmsapp.presentation.adapter.SameItem

sealed class UiStateFilm : Bind, SameItem, Abstract.Film {

    override fun <T> map(mapper: Abstract.FilmMapper<T>): T
        = mapper.map(-1,"","",-1,-1.0f,"","", emptyList())

    override fun bind(filmImage: ImageView, filmName: TextView)
        = Unit
    override fun bind(error: TextView)
        = Unit

    override fun sameId(item: UiStateFilm): Boolean
         = false
    override fun sameId(id: Int): Boolean
        = false

    override fun same(item: UiStateFilm): Boolean
        = false

    override fun same(name: String, description: String): Boolean
        = false

    open fun onItemClick(onItemClickListener: OnItemClickListener<ClickedFilm>)
        = Unit

    object Progress : UiStateFilm()

    class Base(
        private val id: Int,
        private val localized_name: String,
        private val name: String,
        private val shortName: String,
        private val year: Int,
        private val rating: Float,
        private val imageUrl: String,
        private val description: String,
        private val genres: List<String>
    ) : UiStateFilm() {

        override fun bind(filmImage: ImageView, filmName: TextView) {
            Picasso.get().load(imageUrl).into(filmImage)
            filmName.text = shortName
        }

        override fun sameId(item: UiStateFilm): Boolean
            = item.sameId(id)

        override fun sameId(id: Int): Boolean
            = this.id == id

        override fun same(item: UiStateFilm): Boolean
            = item.same(shortName,description)

        override fun same(name: String, description: String): Boolean
            = this.shortName == name && this.description == description

        override fun <T> map(mapper: Abstract.FilmMapper<T>): T
            = mapper.map(id,localized_name,name, year, rating, imageUrl, description, genres)

        override fun onItemClick(onItemClickListener: OnItemClickListener<ClickedFilm>)
            = onItemClickListener.onItemClick(map(MapperToClickedFilm.Base()))
    }

    class Failure(
        private val message: String
    ) : UiStateFilm() {

        override fun bind(error: TextView) {
            error.text = message
        }
    }
}
