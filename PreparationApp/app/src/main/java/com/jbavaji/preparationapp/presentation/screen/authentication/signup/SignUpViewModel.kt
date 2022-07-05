package com.jbavaji.preparationapp.presentation.screen.authentication.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jbavaji.preparationapp.R

class SignUpViewModel : ViewModel() {
    private val PASSWORD_MIN_LENGTH = 6

    private val _inputState = MutableLiveData<SignUpInputState>()
    val userInputState: LiveData<SignUpInputState>
        get() = _inputState

    fun validateUserInput(input: String, validate: UserInputType) {
        if (validate == UserInputType.EMAIL) {
            if (isUserEmailNotValid(input)) {
                _inputState.value = SignUpInputState(
                    emailError = R.string.invalid_email,
                    passwordError = null,
                    isValid = false
                )
            } else {
                _inputState.value = SignUpInputState(
                    emailError = null,
                    passwordError = _inputState.value?.passwordError,
                    isValid = false
                )
            }
        }

        if (validate == UserInputType.PASSWORD) {
            if (isUserPasswordNotValid(input)) {
                _inputState.value = SignUpInputState(
                    emailError = null,
                    passwordError = R.string.invalid_pasword,
                    isValid = false
                )
            } else {
                _inputState.value = SignUpInputState(
                    emailError = _inputState.value?.emailError,
                    passwordError = null,
                    isValid = false
                )
            }
        }

        if (_inputState.value?.emailError == null && _inputState.value?.passwordError == null) {
            _inputState.value =
                SignUpInputState(emailError = null, passwordError = null, isValid = true)
        }
    }

    private fun isUserPasswordNotValid(password: String): Boolean {
        return password.length < PASSWORD_MIN_LENGTH
    }

    private fun isUserEmailNotValid(email: String): Boolean {
        return if (email.isNotBlank() && email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else true
    }

}