package com.example.homegit.model.repository

import android.content.Context
import com.example.homegit.model.api.ApiService
import com.example.homegit.model.api.GithubApiClient
import com.example.homegit.model.api.OkhttpClient
import com.example.homegit.model.api.response.UserResponse
import retrofit2.Call

class UserRepository : GithubApiClient{

    companion object{
        private lateinit var githubUserApi: GithubApiClient

        fun getInstance(context: Context) : UserRepository{
            githubUserApi = ApiService.createService(GithubApiClient::class.java, OkhttpClient.create(context))
            return UserRepository()
        }
    }

    override fun getFollowings(token: String): Call<List<UserResponse>> {
        return githubUserApi.getFollowings(token)
    }

}