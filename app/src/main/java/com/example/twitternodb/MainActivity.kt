package com.example.twitternodb

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_tweet.*

class  MainActivity : AppCompatActivity() , onItemClickListener {
    val tweets = ArrayList<Tweet>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL , false)

        tweets.add( Tweet("Elon Musk", "lorem ipsum blah blah"))
        tweets.add( Tweet("Elon Musk", "lorem ipsum blah blah22"))
        tweets.add( Tweet("Elon Musk", "lorem ipsum blah blah22"))
        tweets.add( Tweet("Elon Musk", "lorem ipsum blah blah22"))



        val adapter = myAdapter(tweets, this)

        recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return true
    }

    // add tweet
    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId) {
        R.id.addTweet->{
            // dialog
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            builder.setTitle("What's on your mind today? ")
            val dialogLayout = inflater.inflate(R.layout.edit_tweet , null)

            val tweetText = dialogLayout.findViewById<EditText>(R.id.editTweet)

            builder.setView(dialogLayout)
            builder.setPositiveButton("Add") { dialog, which ->
                val newTweet = tweetText?.text.toString()
                if(newTweet.isNotBlank()){
                    tweets.add(Tweet("Elon Musk", newTweet))
                }else {
                    Toast.makeText(applicationContext, "Not a valid tweet", Toast.LENGTH_SHORT).show()
                }
            }
            builder.show()
            true
        }
        else-> {
            super.onOptionsItemSelected(item)
        }
    }

//
    override fun onItemLongClicked(tweet: Tweet) {
        tweets.remove(tweet)
        Toast.makeText(applicationContext, tweets.size.toString(), Toast.LENGTH_LONG).show()
        recyclerView.adapter?.notifyDataSetChanged()




}



}
