package com.example.homegit.model.api

import com.example.homegit.model.api.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface GithubApiClient {
    @GET("  users")
    fun getFollowings(
            @Header("Authorization") token: String
    ): Call<List<UserResponse>>
}