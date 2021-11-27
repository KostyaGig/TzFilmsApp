package com.zinoview.tzfilmsapp.data

import com.zinoview.tzfilmsapp.core.BaseFilm
import com.zinoview.tzfilmsapp.data.cloud.CloudDataSource
import com.zinoview.tzfilmsapp.data.cloud.CloudFilm
import com.zinoview.tzfilmsapp.presentation.core.log
import kotlinx.coroutines.delay

interface FilmsRepository<T> {

    suspend fun films() : T

    class Base(
        private val cloudDataSource: CloudDataSource<List<CloudFilm>>,
        private val mapperCloudToDataFilm: MapperCloudToDataFilm,
        private val exceptionMapper: ExceptionMapper<String>
    ) : FilmsRepository<DataFilms> {

        private companion object {
            private const val DELAY = 1300
        }

        override suspend fun films(): DataFilms {
            delay(DELAY.toLong())
            return try {
                val cloudFilms = cloudDataSource.films()
                val dataFilms = cloudFilms.map { cloudFilm ->
                    cloudFilm.map(mapperCloudToDataFilm)
                }
                DataFilms.Success(dataFilms)
            } catch (e: Exception) {
                val errorMessage = exceptionMapper.map(e)
                DataFilms.Failure(errorMessage)
            }
        }
    }

    class Test(
        private val cloudDataSource: CloudDataSource<List<BaseFilm>>
    ) : FilmsRepository<DataFilms> {

        private var returnFailure = false

        override suspend fun films(): DataFilms {
            return if (returnFailure) {
                DataFilms.Failure("No connection")
            } else {
                val baseUsers = cloudDataSource.films()
                returnFailure = true
                DataFilms.Success(
                    baseUsers
                )
            }
        }
    }
}