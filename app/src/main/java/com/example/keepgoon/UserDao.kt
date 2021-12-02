package com.example.keepgoon

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface UserDao {

    companion object{
        const val TAG: String = "LOG"
    }

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<User>>

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)


}