package com.example.newsapp.Model

import com.example.newsapp.Model.Articles

data class NewsData(val status:String,val totalResults:Int,val articles:List<Articles>)
