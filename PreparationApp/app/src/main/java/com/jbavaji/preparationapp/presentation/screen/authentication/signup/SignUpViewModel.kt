package com.jbavaji.preparationapp.presentation.screen.authentication.signup

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jbavaji.core.data.User
import com.jbavaji.core.repository.UserRoomRepository
import com.jbavaji.core.usecase.AddUser
import com.jbavaji.core.usecase.AllUsers
import com.jbavaji.preparationapp.R
import com.jbavaji.preparationapp.framework.UserUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(repository: UserRoomRepository) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val useCases: UserUseCases = UserUseCases(
        AddUser(repository),
        AllUsers(repository)
    )

    private val PASSWORD_MIN_LENGTH = 6

    private val _inputState = MutableLiveData<SignUpInputState>()
    val userInputState: LiveData<SignUpInputState>
        get() = _inputState

    private val _userSaved = MutableLiveData<Boolean>()
    val userSaved: LiveData<Boolean>
        get() = _userSaved

    fun signUpUser(currentUser: User) {
        coroutineScope.launch {
            val signUpUserId = useCases.addUser(currentUser)
            val users = useCases.fetchAllUsers()
            Log.d("fetchAllUsers", "users $users")
            _userSaved.postValue(signUpUserId != 0L)
        }
    }

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

    class SignUpViewModelFactory(private val repository: UserRoomRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SignUpViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}