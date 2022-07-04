package com.jbavaji.preparationapp.presentation.screen.authentication.forgot_password

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jbavaji.preparationapp.R

class ForgotPasswordViewModel : ViewModel() {

    private val _userInputState = MutableLiveData<UserInputState>()

    val userInputState: LiveData<UserInputState>
        get() = _userInputState

    fun validateUserInput(input: String) {
        Log.d("Forgot", "$input is ${isUserEmailNotValid(input)}")
        if (isUserEmailNotValid(input)) {
            _userInputState.value = UserInputState(error = R.string.invalid_email)
        } else {
            _userInputState.value = UserInputState(isValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserEmailNotValid(email: String): Boolean {
        return if (email.isNotBlank() && email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else true
    }
}