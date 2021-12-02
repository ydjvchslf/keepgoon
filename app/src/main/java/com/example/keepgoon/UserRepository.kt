package com.example.keepgoon

import android.app.Application
import androidx.lifecycle.LiveData

class UserRepository(application: Application) {

    companion object{
        const val TAG: String = "LOG"
    }

    // 변수 선언으로 바로 dao 접근하려고 하면 에러!
    private val userDao: UserDao
    private val userList: LiveData<List<User>>

    init { // init에서 반드시 db에서 getInstance() 호출 후 dao통해 db를 접근할 수 있따
        var db = UserDb.getInstance(application)
        userDao = db!!.userDao()
        userList = db.userDao().getAll()
    }

    fun insert(user: User){
        userDao.insert(user)
    }

    fun update(user: User){
        userDao.update(user)
    }

    fun delete(user: User){
        userDao.delete(user)
    }

    fun getAll(): LiveData<List<User>>{
        return userDao.getAll()
    }

}