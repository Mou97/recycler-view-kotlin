package com.example.twitternodb

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_tweet.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class  MainActivity : AppCompatActivity() , onItemClickListener {
    var tweets = ArrayList<Tweet>()

    // fragment management
    private val fragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if(savedInstanceState!=null){
            tweets = savedInstanceState.getSerializable("tweets") as ArrayList<Tweet>

        }

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL , false)


        val adapter = myAdapter(tweets, this)

        recyclerView.adapter = adapter

//        fragment
        val orientation : Int =  resources.configuration.orientation

        val fragmentTransaction = fragmentManager.beginTransaction()

        if(fragmentManager.findFragmentById(R.id.myFragment ) == null){

            if( orientation == Configuration.ORIENTATION_LANDSCAPE) {

                fragmentTransaction.add(R.id.myFragment, DetailsFragment() )
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                fragmentTransaction.commit()
            }

        }



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
                    tweets.add(Tweet("Elon Musk", newTweet, Date()))
                    recyclerView.adapter?.notifyDataSetChanged()
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
        val builder  = AlertDialog.Builder(this)
        with (builder){
            setTitle("Attention")
            setMessage("Are you sure you want to delete this?")
            setPositiveButton("Delete"){ dialog, which ->
                tweets.remove(tweet)
                recyclerView.adapter?.notifyDataSetChanged()
            }
            setNegativeButton("Cancel"){dialog, which ->
                dialog.dismiss()
            }
            show()
        }
    }

    override fun onItemClicked(tweet: Tweet) {
        val orientation : Int =  resources.configuration.orientation


        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            val fragment : DetailsFragment= fragmentManager.findFragmentById(R.id.myFragment) as DetailsFragment
            fragment.newInstance(tweet)

        }else{
            val intent = Intent(this, DetailsActivity::class.java)

            intent.putExtra("Tweet",tweet)
            startActivity(intent)
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("tweets", tweets as Serializable)
    }

}
