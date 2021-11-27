package com.zinoview.tzfilmsapp.presentation

interface Request {


    fun changeState(newState: RequestState)

    fun isNotSending() : Boolean

    class Base : Request {

        private var state: RequestState = RequestState.Empty

        override fun changeState(newState: RequestState) {
            state = newState
        }

        override fun isNotSending() : Boolean
            = state == RequestState.Empty || state == RequestState.Waiting

    }

    sealed class RequestState {

        object Sending : RequestState()
        object Waiting : RequestState()
        object Empty : RequestState()
    }
}