package com.jbavaji.core.repository

import com.jbavaji.core.data.User

interface UserRoomDataResource {

    suspend fun add(user: User): Long
    suspend fun fetchAllUsers(): List<User>

}