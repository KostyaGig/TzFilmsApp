package com.zinoview.tzfilmsapp.presentation.di.module

import com.zinoview.tzfilmsapp.data.DataFilms
import com.zinoview.tzfilmsapp.data.FilmsRepository
import com.zinoview.tzfilmsapp.domain.FilmsInteractor
import com.zinoview.tzfilmsapp.domain.MapperDataToDomainFilm
import com.zinoview.tzfilmsapp.domain.MapperDataToDomainFilms
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideFilmsInteractor(
        filmsRepository: FilmsRepository<DataFilms>
    ) : FilmsInteractor {
        return FilmsInteractor.Base(
            filmsRepository,
            MapperDataToDomainFilms.Base(
                MapperDataToDomainFilm.Base()
            )
        )
    }
}