package com.zinoview.tzfilmsapp.presentation.di.module

import com.zinoview.tzfilmsapp.core.ResourceProvider
import com.zinoview.tzfilmsapp.domain.FilmsInteractor
import com.zinoview.tzfilmsapp.presentation.*
import com.zinoview.tzfilmsapp.presentation.presenter.FilmsPresenter
import com.zinoview.tzfilmsapp.presentation.state.MapperUiToUIStateFilms
import com.zinoview.tzfilmsapp.presentation.state.MapperUiToUiStateFilm
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class UiModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatcher() : CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun provideFilmsPresenter(
        filmsInteractor: FilmsInteractor,
        resourceProvider: ResourceProvider,
        coroutineDispatcher: CoroutineDispatcher
    ) : FilmsPresenter {
        return FilmsPresenter.Base(
            filmsInteractor,
            MapperDomainToUiFilms.Base(
                MapperDomainToUiFilm.Base()
            ),
            MapperUiToUIStateFilms.Base(
                MapperUiToUiStateFilm.Base(
                    StringLengthMapper.Base()
                )
            ),
            Request.Base(),
            FilmsWithCategories.Base(resourceProvider),
            coroutineDispatcher
        )
    }

}