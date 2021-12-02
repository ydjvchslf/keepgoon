package com.example.keepgoon

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserDb? = null

        //db 접근하려면 context를 참조해서 접근해야해!
        @Synchronized // 확인할 것!
        fun getInstance(context: Context): UserDb? {
            if (INSTANCE == null) {
                synchronized(UserDb::class) {
                    INSTANCE = databaseBuilder(context.applicationContext,
                        UserDb::class.java, "user.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }


}