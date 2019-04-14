package com.jintin.clerk.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {

    private var tickState = MutableLiveData<Boolean>()
    private var tickEvent = MutableLiveData<Long>()
    private var disposible: Disposable? = null

    fun getTickState(): MutableLiveData<Boolean> {
        return tickState
    }

    fun getTickEvent(): MutableLiveData<Long> {
        return tickEvent
    }

    override fun onCleared() {
        super.onCleared()
        disposible?.dispose()
    }

    fun toggleTick() {
        if (tickState.value == true) {
            stopTick()
        } else {
            startTick()
        }
    }

    private fun startTick() {
        tickState.value = true
        disposible = Observable.interval(500, TimeUnit.MILLISECONDS, Schedulers.io())
            .subscribe { tick ->
                tickEvent.postValue(tick)
            }
    }

    private fun stopTick() {
        tickState.value = false
        disposible?.dispose()
    }
}