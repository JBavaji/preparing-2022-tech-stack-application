package com.jbavaji.preparationapp.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserEntity(userEntity: UserEntity): Long

    @Query("SELECT * FROM user_table ORDER BY email ASC")
    fun fetchAllUsers(): List<UserEntity>

}