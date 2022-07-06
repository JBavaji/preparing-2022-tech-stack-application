package com.jbavaji.core.usecase

import com.jbavaji.core.repository.UserRoomRepository

class AllUsers(private val userRoomRepository: UserRoomRepository) {
    suspend operator fun invoke() = userRoomRepository.fetchAllUsers()
}