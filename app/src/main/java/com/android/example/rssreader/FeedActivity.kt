package com.android.example.rssreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.example.rssreader.model.Item
import com.android.example.rssreader.model.RSSWrapper
import kotlinx.android.synthetic.main.activity_feed.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


class FeedActivity : AppCompatActivity() {

    /* Used for fetching rss feed */
    private val baseUrl = "https://news.un.org/feed/subscribe/en/news/"

    /* Holds all News Articles for Selected Topic */
    private val rssFeedList = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val feedTopic : FeedTopic = intent.getParcelableExtra<FeedTopic>("feed")!!
        val topicUrl = feedTopic.link

        title = feedTopic.topic

        val recyclerView = findViewById<RecyclerView>(R.id.feed_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RSSFeedAdapter(rssFeedList)


        /* Fetch UN Article Feeds */
        fetchRssFeed(topicUrl, ::onRssResponse, ::onRssFailure)

    }

    private fun onRssResponse(call: Call<RSSWrapper?>?, response: Response<RSSWrapper?>) {
        val feed: RSSWrapper? = response.body()
        if (feed != null) {
            for (item in feed.channel?.items!!) {
                rssFeedList.add(item)
            }
            feed_recycler_view.adapter?.notifyDataSetChanged()
        }
    }

    private fun onRssFailure(call: Call<RSSWrapper?>?, t: Throwable?) {
        Log.e("activity","data fetch request failed")
        Toast.makeText(this,"data could not be fetched",Toast.LENGTH_SHORT).show()
    }

    private fun fetchRssFeed(topicUrl: String, onRssResponse: (call: Call<RSSWrapper?>?, response: Response<RSSWrapper?>) -> Unit, onRssFailure: (call: Call<RSSWrapper?>?, t: Throwable?) -> Unit) {
        /* Define how API requests are made to the rss feed  */
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        /* Connect retrofit to the API feed Interface */
        val unNews = retrofit.create(FeedApi::class.java)

        /* Make API call to get RSS Feed */
        val call: Call<RSSWrapper> = unNews.getFeed(topicUrl)

        /* Process returned data */
        call.enqueue(object : Callback<RSSWrapper?> {
            override fun onResponse(call: Call<RSSWrapper?>?, response: Response<RSSWrapper?>) {
                // val statusCode: Int = response.code()
                onRssResponse(call, response)
            }

            override fun onFailure(call: Call<RSSWrapper?>?, t: Throwable?) {
                onRssFailure(call, t)
            }
        })
    }
}