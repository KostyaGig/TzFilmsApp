package com.zinoview.tzfilmsapp.domain

import com.zinoview.tzfilmsapp.data.DataFilms
import com.zinoview.tzfilmsapp.data.FilmsRepository

interface FilmsInteractor {

    suspend fun films() : DomainFilms

    class Base(
        private val repository: FilmsRepository<DataFilms>,
        private val dataToDomainFilms: MapperDataToDomainFilms
    ) : FilmsInteractor {

        override suspend fun films(): DomainFilms {
            val dataFilms = repository.films()
            return dataFilms.map(dataToDomainFilms)
        }
    }
}