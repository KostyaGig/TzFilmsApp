package com.zinoview.tzfilmsapp.data.cloud

import com.zinoview.tzfilmsapp.core.BaseFilm

interface CloudDataSource<T> {

    suspend fun films() : T

    class Base(
        private val filmsService: FilmsService
    ) : CloudDataSource<List<CloudFilm>> {

        override suspend fun films(): List<CloudFilm>
            = filmsService.films().films()
    }

    class Test : CloudDataSource<List<BaseFilm>> {

        override suspend fun films(): List<BaseFilm> {
            return listOf(
                BaseFilm.Test(
                    1,"TMNT","tmnt.jpg"
                ),
                BaseFilm.Test(
                    2,"1 + 1","1_plus_1.jpg"
                ),
                BaseFilm.Test(
                    3,"Social network","social_network.jpg"
                )
            )
        }
    }
}