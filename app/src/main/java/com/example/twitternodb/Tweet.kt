package com.example.twitternodb

import java.io.Serializable
import java.util.*

data class Tweet(val username : String, val tweet: String , val date : Date) : Serializable