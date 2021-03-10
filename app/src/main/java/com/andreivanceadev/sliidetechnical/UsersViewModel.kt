package com.andreivanceadev.sliidetechnical

import androidx.lifecycle.ViewModel
import com.andreivanceadev.sliidetechnical.extensions.actionToViewState
import com.andreivanceadev.sliidetechnical.extensions.eventToActions
import com.andreivanceadev.sliidetechnical.extensions.mapToUiState
import com.andreivanceadev.sliidetechnical.models.AppEvent
import com.andreivanceadev.sliidetechnical.models.state.AppState
import com.andreivanceadev.sliidetechnical.models.state.AppUIState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class UsersViewModel : ViewModel() {
    private val eventEmitter: PublishSubject<AppEvent> = PublishSubject.create()

    private var disposable: Disposable? = null
    private var previousState: AppState? = null

    val appStateObservable: Observable<AppUIState>

    init {
        val connectableObservable = eventEmitter
            .eventToActions { previousState }
            .actionToViewState()
            .doOnNext { state ->
                previousState = state
            }
            .mapToUiState()
            .replay(1)
        connectableObservable.connect { disposable = it }
        appStateObservable = connectableObservable.subscribeOn(Schedulers.io())
    }

    fun sendEvent(event: AppEvent) {
        eventEmitter.onNext(event)
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}