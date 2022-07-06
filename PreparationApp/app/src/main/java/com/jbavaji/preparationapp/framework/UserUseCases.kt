package com.jbavaji.preparationapp.framework

import com.jbavaji.core.usecase.AddUser
import com.jbavaji.core.usecase.AllUsers

data class UserUseCases(
    val addUser: AddUser,
    val fetchAllUsers: AllUsers
)
