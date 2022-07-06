package com.jbavaji.preparationapp.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jbavaji.core.data.User

@Entity(tableName = "user_table")
class UserEntity(

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "creation_date")
    val creationTime: Long,

    @ColumnInfo(name = "update_time")
    val updateTime: Long,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val userId: Long = 0L
) {
    companion object {
        fun fromUser(user: User) = UserEntity(
            email = user.email,
            password = user.password,
            creationTime = user.creationTime,
            updateTime = user.updateTime
        )
    }

    fun toUser() = User(email, password, creationTime, updateTime, userId)
}