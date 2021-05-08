package com.example.homegit.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homegit.model.api.response.UserResponse
import com.example.homegit.model.repository.UserRepository
import com.example.homegit.util.Const
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val user = MutableLiveData<List<UserResponse>>()

    private lateinit var repository: UserRepository

    fun initRepository(context: Context) {
        repository = UserRepository.getInstance(context)
    }

    //Sebaiknya hal-hal penting seperti api key, token, dsb. yang berbentuk konstan
    //bisa ditaruh di build.gradle (Project)
    private val token = "token " + Const.TOKEN

    fun followingUser() {
        repository.getFollowings(token).enqueue(object : Callback<List<UserResponse>>{
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                response.body()?.let {
                    user.value = it
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }

    fun getUser(): LiveData<List<UserResponse>> {
        return user
    }
}