package com.example.twitternodb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class myAdapter (val tweetsList: ArrayList<Tweet>, val itemClickListener: onItemClickListener) : RecyclerView.Adapter<myAdapter.ViewHolder> () {

    class  ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val username = itemView.findViewById(R.id.username) as TextView
        val tweet = itemView.findViewById(R.id.tweet) as TextView

        fun bind(newTweet : Tweet , clickListener: onItemClickListener){
            username.text = newTweet.username
            tweet.text = newTweet.tweet

            itemView.setOnLongClickListener {
                clickListener.onItemLongClicked(newTweet)
                
                return@setOnLongClickListener true
            }

            itemView.setOnClickListener{
                clickListener.onItemClicked(newTweet)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.tweets_list, parent, false)
        return  ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  tweetsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tweet : Tweet = tweetsList[position]

//        holder?.username?.text = tweet.username
//        holder?.tweet?.text = tweet.tweet
        holder?.bind(tweet, itemClickListener )


    }
}

interface  onItemClickListener {
    fun onItemLongClicked(tweet : Tweet)
    fun onItemClicked(tweet: Tweet)
}