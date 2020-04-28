package com.example.twitternodb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val tw = intent.getSerializableExtra("Tweet") as? Tweet
        Log.i("Serial", tw.toString())
        username.text = tw?.username
        tweet.text = tw?.tweet
        date.text = tw?.date.toString()
    }
}
