package com.example.keepgoon

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

//ViewModel() 을 상속받을때와 Apllication 상속 차이점?
class UserViewModel(application: Application): AndroidViewModel(application) {

    companion object{
        const val TAG: String = "LOG"
    }

    private val repository = UserRepository(application)
    private val items = repository.getAll() //retrun LiveData<List<User>>

    fun insert(user: User){
        repository.insert(user)
    }

    fun delete(user: User){
        repository.delete(user)
    }

    fun getAll(): LiveData<List<User>> {
        return items
    }

}