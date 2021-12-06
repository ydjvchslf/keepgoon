package com.example.keepgoon

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface UserDao {

    companion object{
        const val TAG: String = "LOG"
    }

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAll(): LiveData<List<User>>

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("DELETE FROM users")
    fun deleteAll()

    @Delete
    fun deleteOne(user: User)


}