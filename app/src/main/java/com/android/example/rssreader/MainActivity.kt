package com.android.example.rssreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "UN News Feeds"

        val topics = mutableListOf<FeedTopic>(
            FeedTopic("Health", "health"),
            FeedTopic("UN Affairs", "un-affairs"),
            FeedTopic("Law and Crime Prevention", "law-and-crime-prevention"),
            FeedTopic("Human Rights", "human-rights"),
            FeedTopic("Humanitarian Aid", "humanitarian-aid"),
            FeedTopic("Climate Change", "climate-change"),
            FeedTopic("Culture and Education", "culture-and-education"),
            FeedTopic("Economic Development", "economic-development"),
            FeedTopic("Women", "women"),
            FeedTopic("Peace and Security", "peace-and-security"),
            FeedTopic("Migrants and Refugees", "migrants-and-refugees"),
            FeedTopic("SDGs", "sdgs")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TopicAdapter(topics)

    }
}
