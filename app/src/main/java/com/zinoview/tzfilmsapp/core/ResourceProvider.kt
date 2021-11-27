package com.zinoview.tzfilmsapp.core

import android.content.Context
import androidx.annotation.StringRes
import com.zinoview.tzfilmsapp.R

interface ResourceProvider {

    fun string(@StringRes id: Int) : String

    class Base(
        private val context: Context
    ) : ResourceProvider {

        override fun string(id: Int): String
            = context.getString(id)
    }

    class Test : ResourceProvider {
        override fun string(id: Int): String
            = when(id) {
                R.string.no_connection_text -> "No connection"
                R.string.some_went_wrong_text -> "Some went wrong"
                else -> ""
            }

    }
}