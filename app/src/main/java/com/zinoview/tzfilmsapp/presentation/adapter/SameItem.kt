package com.zinoview.tzfilmsapp.presentation.adapter

import com.zinoview.tzfilmsapp.presentation.state.UiStateFilm

interface SameItem {

    fun same(item: UiStateFilm) : Boolean

    fun sameId(item: UiStateFilm) : Boolean

    fun same(name: String, description: String) : Boolean

    fun sameId(id: Int) : Boolean
}