/**
 * DO NOT CHANGE
 */

package com.android.example.rssreader

import com.android.example.rssreader.model.RSSWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FeedApi {
    /**
     * The base url being used is "https://news.un.org/feed/subscribe/en/news/".
     * The base url is passed in on FeedActivity Page
     */

    // Get UN News Feed based on @feed_topic
    @GET("topic/{feed_topic}/feed/rss.xml")
    fun getFeed(@Path(value =  "feed_topic", encoded = true) feed_topic : String) : Call<RSSWrapper>
}