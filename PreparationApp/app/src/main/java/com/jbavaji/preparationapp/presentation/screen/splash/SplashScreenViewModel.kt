package com.jbavaji.preparationapp.presentation.screen.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private var _splashLifeCycle = MutableLiveData<Boolean>()

    val splashLifeCycle
        get() = _splashLifeCycle

    fun loadingApp() {
        _splashLifeCycle.postValue(false)
        GlobalScope.launch {
            delay(3000L)
            _splashLifeCycle.postValue(true)
        }
    }
}