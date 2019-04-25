package com.jintin.clerk.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * ViewModel for MainActivity
 */
class MainViewModel : ViewModel() {

    private var tickState = MutableLiveData<Boolean>()
    private var tickEvent = MutableLiveData<Long>()
    private var disposable: Disposable? = null

    /**
     * Get tick state of on/off
     */
    fun getTickState() = tickState

    /**
     * Get tick LiveData to observe when it's tick
     */
    fun getTickEvent() = tickEvent

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    /**
     * Toggle the tick state
     */
    fun toggleTick() {
        if (tickState.value == true) {
            stopTick()
        } else {
            startTick()
        }
    }

    private fun startTick() {
        tickState.value = true
        disposable = Observable.interval(500, TimeUnit.MILLISECONDS, Schedulers.io())
            .subscribe { tick ->
                tickEvent.postValue(tick)
            }
    }

    private fun stopTick() {
        tickState.value = false
        disposable?.dispose()
    }
}