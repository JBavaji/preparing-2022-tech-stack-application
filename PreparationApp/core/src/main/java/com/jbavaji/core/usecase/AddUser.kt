package com.jbavaji.core.usecase

import com.jbavaji.core.data.User
import com.jbavaji.core.repository.UserRoomRepository

class AddUser(private val userRepository: UserRoomRepository) {
    suspend operator fun invoke(user: User) = userRepository.addUser(user)
}