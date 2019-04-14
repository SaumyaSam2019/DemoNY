package com.demo.nyarticles.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.demo.nyarticles.R
import com.demo.nyarticles.listeners.RecyclerViewClickListener
import com.demo.nyarticles.models.ArticlesModel
import com.squareup.picasso.Picasso


class ArticleListAdapter(val context: Context, val articles : ArrayList<ArticlesModel.Result>, val recyclerViewClickListener: RecyclerViewClickListener) : RecyclerView.Adapter<ArticleListAdapter.AViewHolder>(){

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): AViewHolder {
        val view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_list_article, parent, false)
        return AViewHolder(view)
    }

    override fun getItemCount(): Int {
       return articles.size
    }

    override fun onBindViewHolder(holder: AViewHolder, position: Int) {
        var item = articles.get(position)
        var imgUrl = ""
        if(item != null) {
            holder.text_header.text = item.title
            holder.text_details.text = item.byline
            holder.text_issued.text = item.publishedDate
            holder.article_container.setOnClickListener{
                recyclerViewClickListener.onItemClick(position)
            }
            if(item.media!= null && item.media!!.size>0 && item.media!!.get(0).mediaMetadata!= null && item.media!!.get(0).mediaMetadata.size > 1) {
                imgUrl = item.media!!.get(0).mediaMetadata.get(1).url!!
            }
            Picasso.get().load(imgUrl).into(holder.image_news)
        }
    }


    inner class AViewHolder(val mView: View):RecyclerView.ViewHolder(mView) {
        val image_news: ImageView
        val text_header: TextView
        val text_details: TextView
        val text_issued: TextView
        val article_container: LinearLayout

        init{
            image_news = mView.findViewById(R.id.image_news)
            text_header = mView.findViewById(R.id.text_header)
            text_details = mView.findViewById(R.id.text_details)
            text_issued = mView.findViewById(R.id.text_issued)
            article_container = mView.findViewById(R.id.article_container)
        }

    }
}