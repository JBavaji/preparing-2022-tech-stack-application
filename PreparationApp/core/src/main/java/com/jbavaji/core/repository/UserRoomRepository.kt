package com.jbavaji.core.repository

import com.jbavaji.core.data.User

class UserRoomRepository(private val dataSource: UserRoomDataResource) {
    suspend fun addUser(user: User) = dataSource.add(user)
}