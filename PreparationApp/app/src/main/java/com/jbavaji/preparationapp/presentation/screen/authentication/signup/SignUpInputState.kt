package com.jbavaji.preparationapp.presentation.screen.authentication.signup

data class SignUpInputState(
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val isValid: Boolean = false
)


