package com.jbavaji.preparationapp.framework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jbavaji.preparationapp.framework.db.UserDao
import com.jbavaji.preparationapp.framework.db.UserEntity

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "note.db"

        private var instance: UserRoomDatabase? = null

        //  https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }

        private fun create(context: Context): UserRoomDatabase = Room
            .databaseBuilder(
                context.applicationContext,
                UserRoomDatabase::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()

        fun getInstance(context: Context): UserRoomDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun userDao(): UserDao
}
