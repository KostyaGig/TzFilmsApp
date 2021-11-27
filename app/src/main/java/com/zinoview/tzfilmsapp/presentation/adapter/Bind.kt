package com.zinoview.tzfilmsapp.presentation.adapter

import android.widget.ImageView
import android.widget.TextView

interface Bind {

    fun bind(filmImage: ImageView,filmName: TextView)

    fun bind(error: TextView)
}