package com.simuel.autocrypt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simuel.domain.model.Center
import com.simuel.domain.usecase.CenterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: CenterUseCase
) : ViewModel() {
    private val mutex = Mutex()

    private val requestCounter = MutableLiveData(10)

    private val _centerList = MutableLiveData<List<Center>>(emptyList())
    val centerList: LiveData<List<Center>>
        get() = _centerList

    private val _center = MutableLiveData<Center>()
        val center: LiveData<Center>
            get() = _center

    private val _progress = MutableLiveData<Int>(0)
    val progress: LiveData<Int>
        get() = _progress

    private val _currentPosition = MutableLiveData<Event<Unit>>()
        val currentPosition: LiveData<Event<Unit>>
            get() = _currentPosition

    init {
        loadCenter()
    }

    private fun loadCenter() = viewModelScope.launch {
        animateProgressTo(80, 2000)
        val apiCenter = (1..10).map { page ->
            async {
                val result = useCase.getRemoteCenter(page)
                result.forEach { center ->
                    saveCenter(center)
                }
                updateProgress()
                handleEventAfterLastRequest()
            }
        }
        apiCenter.joinAll()
    }
    fun setCenter(center: Center) {
        _center.value = center
    }

    fun setCenterList() = viewModelScope.launch {
        _centerList.value = useCase.getLocalCenter()
    }

    fun updateCurrentPosition() {
        _currentPosition.value = Event(Unit)
    }

    private suspend fun animateProgressTo(target: Int, duration: Long) {
        val currentProgress = _progress.value ?: 0
        val stepCount = 100
        val stepDuration = duration / stepCount
        val stepSize = (target - currentProgress) / stepCount.toFloat()

        for (i in 1..stepCount) {
            delay(stepDuration)
            _progress.postValue((currentProgress + stepSize * i).toInt())
        }
    }

    private suspend fun updateProgress() {
        mutex.withLock {
            _progress.postValue((_progress.value ?: 0) + 1)
        }
    }

    private suspend fun handleEventAfterLastRequest() {
        mutex.withLock {
            requestCounter.value = (requestCounter.value ?: 10) - 1
            if (requestCounter.value == 0) {
                animateProgressTo(100, 400)
            }
        }
    }

    private suspend fun saveCenter(center: Center) {
        useCase.saveCenter(center)
    }
}