package com.example.keepgoon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.example.keepgoon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG: String = "LOG"
    }

    private lateinit var binding: ActivityMainBinding //view binding
    private lateinit var viewModel: UserViewModel //view model
    private lateinit var userAdapter: UserAdapter // adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "MainActivity - onCreate() 호출")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()




    }

    // 리사이클러뷰 세팅
    fun initRecyclerView(){
        //어댑터 객체 선언 어케하나?

        binding.rvView.adapter = adapter
        binding.rvView.layoutManager = LinearLayout()

    }


}