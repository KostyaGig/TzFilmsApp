package com.zinoview.tzfilmsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zinoview.tzfilmsapp.databinding.FailureItemBinding
import com.zinoview.tzfilmsapp.databinding.FilmItemBinding
import com.zinoview.tzfilmsapp.databinding.ProgressItemBinding
import com.zinoview.tzfilmsapp.presentation.state.UiStateFilm

interface FilmsAdapter {

    fun update(newList: List<UiStateFilm>)

    class Base : FilmsAdapter, RecyclerView.Adapter<Base.ViewHolder>() {

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
            private const val FAILURE = 3
        }

        override fun getItemViewType(position: Int): Int {
            return when(users[position]) {
                is UiStateFilm.Progress -> PROGRESS
                is UiStateFilm.Base -> BASE
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
                    )
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
                ) : ViewHolder(view.root) {

                override fun bind(film: UiStateFilm) {
                    film.bind(view.filmImage,view.filmName)
                }
            }
            class Failure(private val view: FailureItemBinding) : ViewHolder(view.root) {

                override fun bind(film: UiStateFilm)
                    = film.bind(view.errorTv)
            }
        }
    }
}
