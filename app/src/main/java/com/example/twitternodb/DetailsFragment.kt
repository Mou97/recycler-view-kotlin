package com.example.twitternodb

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.tweet_details.*
import kotlinx.android.synthetic.main.tweet_details.view.*

class DetailsFragment: Fragment() {

    private lateinit var username : TextView;
    private lateinit var tweet  : TextView;
    private lateinit var date : TextView ;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView =  inflater.inflate(R.layout.tweet_details, container, false)
        username =  rootView.username as TextView
        tweet = rootView.tweet as TextView
        date = rootView.date as TextView

        return  rootView
    }


    fun newInstance(newTweet : Tweet) {
        username.text = newTweet.username
        tweet.text = newTweet.tweet
        date.text = newTweet.date.toString()
    }


}