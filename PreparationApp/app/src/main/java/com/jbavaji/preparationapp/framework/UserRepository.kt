package com.jbavaji.preparationapp.framework

import android.content.Context
import com.jbavaji.core.data.User
import com.jbavaji.core.repository.UserRoomDataResource
import com.jbavaji.preparationapp.framework.db.UserEntity

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class UserRepository(context: Context) : UserRoomDataResource {

    private val userDao = UserRoomDatabase.getInstance(context).userDao()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    override suspend fun add(user: User): Long = userDao.addUserEntity(
        userEntity = UserEntity.fromUser(user)
    )
}