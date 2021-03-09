package com.android.example.rssreader

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.rssreader.model.Item


class RSSFeedAdapter(private val mWordList: MutableList<Item>): RecyclerView.Adapter<RSSFeedAdapter.WordViewHolder>() {

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val entryView: LinearLayout = itemView.findViewById<LinearLayout>(R.id.articleEntry)
        val dateView: TextView = itemView.findViewById<TextView>(R.id.topicName)
        val titleView: TextView = itemView.findViewById<TextView>(R.id.title)
        val descriptionView: TextView = itemView.findViewById<TextView>(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rss_feed_item, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        val dateList = mWordList[position].pubDate.split(" ")
        val dateString = dateList[2] + " " + dateList[1] + ", " + dateList[3]


        holder.dateView.text = dateString
        mWordList[position].pubDate = dateString + " - " + dateList[4]
        holder.titleView.text = mWordList[position].title
        holder.descriptionView.text = mWordList[position].description
        holder.entryView.setOnClickListener {
            val intent = Intent(it.context, NewsArticleActivity::class.java)
            intent.putExtra("article", mWordList[position])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mWordList.size
    }
}