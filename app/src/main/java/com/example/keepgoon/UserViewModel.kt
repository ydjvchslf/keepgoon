package com.example.keepgoon

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

//ViewModel() 을 상속받을때와 Apllication 상속 차이점?
class UserViewModel(application: Application): AndroidViewModel(application) {

    companion object{
        const val TAG: String = "LOG"
    }

    private val repository = UserRepository(application)
    private val items = repository.getAll() //return LiveData<List<User>> -> 옵저버 객체가 온다

    fun insert(inputData: HashMap<String, Any>){

        Log.d(TAG, "[UserViewModel] - insert() 호출")

        val name = inputData[("name")]
        val age = inputData[("age")]

        val user = User(0, name as String, age as Int)

        repository.insert(user)
    }

    fun deleteAll(){
        Log.d(TAG, "[UserViewModel] - deleteAll() 호출")

        repository.deleteAll()
    }

    fun deleteOne(user: User){
        Log.d(TAG, "[UserViewModel] - deleteOne() 호출")

        repository.deleteOne(user)
    }

    fun getAll(): LiveData<List<User>> {
        return items
    }

}