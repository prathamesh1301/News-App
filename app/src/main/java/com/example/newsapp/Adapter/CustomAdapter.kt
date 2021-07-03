package com.example.newsapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Activities.DetailsActivity
import com.example.newsapp.R
import com.squareup.picasso.Picasso;


class CustomAdapter(val context:Context,val authors:MutableList<String>,val titles:MutableList<String>,val desc:MutableList<String>,
                    val urlImages:MutableList<String>,val url:MutableList<String>):RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    class MyViewHolder(val itemView:View):RecyclerView.ViewHolder(itemView){
        var authorName:TextView=itemView.findViewById(R.id.newsAuthor)
        var titleName:TextView=itemView.findViewById(R.id.newsTitle)
        var image:ImageView=itemView.findViewById(R.id.newsImg)
        var cardView:CardView=itemView.findViewById(R.id.cardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(authors.get(position)!=null){
            holder.authorName.text=authors.get(position)
        }else{
            holder.authorName.text="No source"
        }

        holder.titleName.text=titles.get(position)
        Picasso.get().load(urlImages.get(position)).into(holder.image)
        holder.cardView.setOnClickListener {
            var intent=Intent(context, DetailsActivity::class.java)
            intent.putExtra("author",authors.get(position))
            intent.putExtra("title",titles.get(position))
            intent.putExtra("desc",desc.get(position))
            intent.putExtra("img",urlImages.get(position))
            intent.putExtra("url",url.get(position))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return titles.size
    }
}