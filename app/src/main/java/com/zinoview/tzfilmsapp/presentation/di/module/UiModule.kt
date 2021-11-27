package com.zinoview.tzfilmsapp.presentation.di.module

import com.zinoview.tzfilmsapp.domain.FilmsInteractor
import com.zinoview.tzfilmsapp.presentation.presenter.FilmsPresenter
import com.zinoview.tzfilmsapp.presentation.MapperDomainToUiFilm
import com.zinoview.tzfilmsapp.presentation.MapperDomainToUiFilms
import com.zinoview.tzfilmsapp.presentation.StringLengthMapper
import com.zinoview.tzfilmsapp.presentation.state.MapperUiToUIStateFilms
import com.zinoview.tzfilmsapp.presentation.state.MapperUiToUiStateFilm
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class UiModule {

    @Provides
    fun provideCoroutineDispatcher() : CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    fun provideFilmsPresenter(
        filmsInteractor: FilmsInteractor,
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
            coroutineDispatcher
        )
    }

}