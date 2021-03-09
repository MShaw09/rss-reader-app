package com.android.example.rssreader

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.example.rssreader.model.Item


class NewsArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_article)

        title = ""

        val newsItem : Item = intent.getParcelableExtra<Item>("article")!!

        val dateView: TextView = this.findViewById<TextView>(R.id.articleDate)
        val titleView: TextView = this.findViewById<TextView>(R.id.articleTitle)
        val descView: TextView = this.findViewById<TextView>(R.id.articleContent)

        dateView.text = newsItem.pubDate
        titleView.text = newsItem.title
        descView.text = newsItem.description

        val button: TextView = findViewById<TextView>(R.id.urlView)

        button.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                val uri: Uri = Uri.parse(newsItem.link)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        })

    }
}