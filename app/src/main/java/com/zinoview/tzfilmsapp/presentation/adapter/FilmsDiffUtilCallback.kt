package com.zinoview.tzfilmsapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.zinoview.tzfilmsapp.presentation.state.UiStateFilm

class FilmsDiffUtilCallback(
    private val oldList: List<UiStateFilm>,
    private val newList: List<UiStateFilm>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int
        = oldList.size

    override fun getNewListSize(): Int
        = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
        = oldList[oldItemPosition].sameId(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
        = oldList[oldItemPosition].same(newList[newItemPosition])



}