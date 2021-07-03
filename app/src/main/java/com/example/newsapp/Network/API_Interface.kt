package com.example.newsapp.Network


import com.example.newsapp.Model.NewsData
import retrofit2.Call
import retrofit2.http.GET
interface API_Interface {
    @GET("top-headlines?country=us&apiKey=ee7a58936e9546c6a9e96029710f9b13")
    fun getNews():Call<NewsData>
}