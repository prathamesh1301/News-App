package com.example.newsapp.Activities

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Network.API_Interface
import com.example.newsapp.Adapter.CustomAdapter
import com.example.newsapp.Model.NewsData
import com.example.newsapp.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var progressDialog:ProgressDialog
    var authors:  MutableList<String> = mutableListOf<String>()
    var titles:  MutableList<String> = mutableListOf<String>()
    var desc: MutableList<String> = mutableListOf<String>()
    var imageURL:MutableList<String> = mutableListOf<String>()
    var url: MutableList<String> =  mutableListOf<String>()
    var content:  MutableList<String> = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Loading")
        progressDialog.setCancelable(false)
        progressDialog.show()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getAllNews()
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


    }

    private fun getAllNews() {
        val retrofitBuilder=Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API_Interface::class.java)
        retrofitBuilder.getNews().enqueue(object :Callback<NewsData>{
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                if(response.isSuccessful){
                    val ans=response.body()
                    if (ans != null) {
                       for(obj in ans.articles){
                           authors.add(obj.author)
                           titles.add(obj.title)
                           desc.add(obj.description)
                           imageURL.add(obj.urlToImage)
                           url.add(obj.url)
                           content.add(obj.content)
                       }
                        val adapter= CustomAdapter(applicationContext,authors,titles,desc,imageURL,url)
                        adapter.notifyDataSetChanged()
                        recyclerView.adapter=adapter

                        progressDialog.dismiss()

                    }
                }
            }

            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
            }
        })

    }
}