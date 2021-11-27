package com.zinoview.tzfilmsapp.presentation.presenter

import com.zinoview.tzfilmsapp.domain.FilmsInteractor
import com.zinoview.tzfilmsapp.presentation.MapperDomainToUiFilms
import com.zinoview.tzfilmsapp.presentation.Request
import com.zinoview.tzfilmsapp.presentation.core.log
import com.zinoview.tzfilmsapp.presentation.presenter.view.FilmsView
import com.zinoview.tzfilmsapp.presentation.state.MapperUiToUIStateFilms
import com.zinoview.tzfilmsapp.presentation.state.UiStateFilm
import kotlinx.coroutines.*

interface FilmsPresenter {

    fun films()

    fun subscribe(view: FilmsView)

    fun unSubscribe()

    class Base(
        private val interactor: FilmsInteractor,
        private val domainToUiFilms: MapperDomainToUiFilms,
        private val uiToUIStateFilms: MapperUiToUIStateFilms,
        private val request: Request,
        dispatcher: CoroutineDispatcher
    ) : FilmsPresenter {

        private var view: FilmsView = FilmsView.Empty
        private val scope = CoroutineScope(dispatcher)

        override fun films() {
            if (request.isNotSending()) {
                request.changeState(Request.RequestState.Sending)
                view.updateState(listOf(UiStateFilm.Progress))
                scope.launch {
                    val domainFilms = interactor.films()
                    val uiFilms = domainFilms.map(domainToUiFilms)
                    val uiStateFilm = uiFilms.map(uiToUIStateFilms)

                    withContext(Dispatchers.Main) {
                        view.updateState(uiStateFilm)
                        request.changeState(Request.RequestState.Waiting)
                    }
                }
            } else {
                view.updateState(listOf(UiStateFilm.Progress))
            }
        }

        override fun subscribe(view: FilmsView)  {
            this.view = view
        }

        override fun unSubscribe() {
            request.changeState(Request.RequestState.Empty)
            this.view = FilmsView.Empty
        }

    }
}