package com.example.newsapp.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val authorName=intent.getStringExtra("author")
        val title=intent.getStringExtra("title")
        val desc=intent.getStringExtra("desc")
        val img=intent.getStringExtra("img")
        val url=intent.getStringExtra("url")

        Picasso.get().load(img).into(detailsImg)
        detailsTitle.text=title
        detailsDesc.text=desc
        if(authorName!=null){
            detailsAuthor.text="-"+authorName
        }else{
            detailsAuthor.text="-No source"
        }


        moreDetails.setOnClickListener {

            val uri: Uri = Uri.parse(url)
            val i=Intent(Intent.ACTION_VIEW,uri)
            startActivity(i)
        }
    }
}