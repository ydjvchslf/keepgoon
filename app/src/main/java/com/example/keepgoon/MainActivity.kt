package com.example.keepgoon

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keepgoon.MainActivity.Companion.TAG
import com.example.keepgoon.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "LOG"
    }

    private lateinit var binding: ActivityMainBinding //view binding
    private lateinit var viewModel: UserViewModel //view model
    private lateinit var userAdapter: UserAdapter // adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "[MainActivity] - onCreate() 호출")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //view model 가져오기
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(UserViewModel::class.java)

        initRecyclerView()

        clickEvent()


    }

    // 어댑터 이용해서 리사이클러뷰 세팅
    private fun initRecyclerView() {
        viewModel.getAll().observe(this) { users ->
            binding.rvView.layoutManager = LinearLayoutManager(this)
            userAdapter = UserAdapter(users)
        }
        binding.rvView.adapter = userAdapter
    }

//        viewModel.getAll().observe(this) {
//            binding.rvView.apply {
//               layoutManager = LinearLayoutManager(this@MainActivity)
//               userAdapter = UserAdapter(it)
//               adapter = userAdapter
//            }
//        }


    private fun clickEvent() {

        binding.addBtn.setOnClickListener {

            Log.d(TAG, "[MainActivity] - clickEvent() - addBtn 클릭")

            val inputName = binding.inputName.text.toString()
            val inputAge = binding.inputAge.text.toString().toInt()

            Log.d(TAG, "[MainActivity] - 받은 data 확인 => name: ${inputName}, age: $inputAge")

            //user에게 받은 input -> view로 넘기기 (Q.보통 뭘로 넘기나유? 나는 map이 좋아)
            val inputData = HashMap<String, Any>()
            inputData["name"] = inputName
            inputData.put("age", inputAge)

            Log.d(
                TAG,
                "[MainActivity] - hashMap에 잘 담겼니? name: ${inputData[("name")]}, age: ${inputData[("age")]}"
            )

//            //UI Thread는 긴 시간이 걸리는 작업을 중단하고 에러를 발생시킵니다. 때문에 db 처리 작업을 이렇게 하려면 오류 발생 -> 비동기로 코루틴 사용
//            viewModel.insert(inputData)
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.insert(inputData)
            }

        }

        binding.deleteBtn.setOnClickListener {

            Log.d(TAG, "[MainActivity] -  clickEvent() - deleteBtn 클릭")

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.deleteAll()
            }
        }

        binding.rvView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                Log.d(TAG, "[MainActivity] -  rvView 터치 - onInterceptTouchEvent()")

                return true
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                Log.d(TAG, "[MainActivity] -  rvView 터치 - onTouchEvent()")
                showDialog()
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                Log.d(TAG, "[MainActivity] -  rvView 터치 - onRequestDisallowInterceptTouchEvent()")
                TODO("Not yet implemented")
            }


        })

    }


    private fun showDialog() {
        // 다이얼로그를 생성하기 위해 Builder 클래스 생성자를 이용해 줍니다.
        val builder = AlertDialog.Builder(this)
        builder.setTitle("삭제하시겠습니까?")
            .setMessage("진짜진짜?")
            .setPositiveButton("확인",
                DialogInterface.OnClickListener { _, _ ->
                    Log.d(TAG, "[MainActivity] - showDialog() - 확인누름")

                    //터치한 rvView 한개 데이터만 삭제

                })
            .setNegativeButton("취소",
                DialogInterface.OnClickListener { dialog, id ->
                    Log.d(TAG, "[MainActivity] - showDialog() - 취소누름")
                })
        // 다이얼로그를 띄워주기
        builder.show()

    }
}




