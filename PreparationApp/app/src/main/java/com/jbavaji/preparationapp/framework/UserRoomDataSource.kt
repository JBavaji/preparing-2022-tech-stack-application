package com.jbavaji.preparationapp.framework

import android.content.Context
import com.jbavaji.core.data.User
import com.jbavaji.core.repository.UserRoomDataResource
import com.jbavaji.preparationapp.framework.db.UserEntity

class UserRoomDataSource(context: Context): UserRoomDataResource {

    private val userDao = UserRoomDatabase.getInstance(context).userDao()

    override suspend fun add(user: User): Long = userDao.addUserEntity(
        userEntity = UserEntity.fromUser(user)
    )
}