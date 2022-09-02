package com.emonics.fooddelivery.Database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null
        private var userId: Int = 0

        fun getDatabase(context: Context) :AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        fun setUserId(id: Int) {
            userId = id
        }

        fun getUserId(): Int {
            return userId
        }
    }
}