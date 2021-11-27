package com.zinoview.tzfilmsapp.presentation.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzfilmsapp.R
import com.zinoview.tzfilmsapp.databinding.*
import com.zinoview.tzfilmsapp.presentation.ClickedFilm
import com.zinoview.tzfilmsapp.presentation.core.log
import com.zinoview.tzfilmsapp.presentation.state.UiStateFilm

interface FilmsAdapter {

    fun update(newList: List<UiStateFilm>)

    class Base(
        private val onItemClickListener: OnItemClickListener<ClickedFilm>,
        private val genreItemClickListener: OnItemClickListener<String>,
    ) : FilmsAdapter, RecyclerView.Adapter<Base.ViewHolder>() {

        private val users = ArrayList<UiStateFilm>()

        override fun update(newList: List<UiStateFilm>) {
            val diffUtilCallback = FilmsDiffUtilCallback(users,newList)
            val result = DiffUtil.calculateDiff(diffUtilCallback)
            users.clear()
            users.addAll(newList)
            result.dispatchUpdatesTo(this)
        }

        private companion object {

            private const val PROGRESS = 1
            private const val BASE = 2
            private const val GENRE = 3
            private const val CATEGORY = 4
            private const val FAILURE = 5
        }

        override fun getItemViewType(position: Int): Int {
            return when (users[position]) {
                is UiStateFilm.Progress -> PROGRESS
                is UiStateFilm.Base -> BASE
                is UiStateFilm.Genre -> GENRE
                is UiStateFilm.Category -> CATEGORY
                else -> FAILURE
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return when (viewType) {
                PROGRESS -> ViewHolder.Progress(
                    ProgressItemBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
                BASE -> ViewHolder.Base(
                    FilmItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onItemClickListener
                )
                GENRE -> ViewHolder.Genre(
                    GenreItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),
                    genreItemClickListener
                )
                CATEGORY -> ViewHolder.Category(
                    CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                )
                else -> ViewHolder.Failure(
                    FailureItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val user = users[position]
            holder.bind(user)
        }

        override fun getItemCount(): Int
            = users.size

        abstract class ViewHolder(
            view: View
        ) : RecyclerView.ViewHolder(view) {

            open fun bind(film: UiStateFilm) {}

            class Progress(view: ProgressItemBinding) : ViewHolder(view.root)

            class Base(
                private val view: FilmItemBinding,
                private val onItemClickListener: OnItemClickListener<ClickedFilm>
                ) : ViewHolder(view.root) {

                override fun bind(film: UiStateFilm) {
                    film.bind(view.filmImage,view.filmName)

                    itemView.setOnClickListener {
                        film.onItemClick(onItemClickListener)
                    }
                }
            }

            class Genre(
                private val view: GenreItemBinding,
                private val genreItemClickListener: OnItemClickListener<String>
            ) : ViewHolder(view.root) {

                @RequiresApi(Build.VERSION_CODES.M)
                override fun bind(film: UiStateFilm) {
                    film.bind(view.genreTv)

                    itemView.setOnClickListener {
                        film.onGenreItemClick(genreItemClickListener)
                    }


                    film.bind(view.genreTv)
                    film.bind(view.genreItem)
                }
            }

            class Category(
                private val view: CategoryItemBinding
            ) : ViewHolder(view.root) {

                override fun bind(film: UiStateFilm) {
                    film.bind(view.categoryTv)
                }
            }

            class Failure(private val view: FailureItemBinding) : ViewHolder(view.root) {

                override fun bind(film: UiStateFilm)
                    = film.bind(view.errorTv)
            }
        }
    }
}
