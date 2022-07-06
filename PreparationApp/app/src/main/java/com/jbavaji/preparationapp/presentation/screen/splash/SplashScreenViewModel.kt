package com.jbavaji.preparationapp.presentation.screen.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private var _splashLifeCycle = MutableLiveData<Boolean>()

    val splashLifeCycle
        get() = _splashLifeCycle

    fun loadingApp() {
        _splashLifeCycle.postValue(false)
        viewModelScope.launch {
            delay(3000L)
            _splashLifeCycle.postValue(true)
        }
    }
}