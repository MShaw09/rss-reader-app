package com.android.example.rssreader

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TopicAdapter(private val mWordList: MutableList<FeedTopic>): RecyclerView.Adapter<TopicAdapter.WordViewHolder>() {

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val entryView: LinearLayout = itemView.findViewById<LinearLayout>(R.id.topicEntry)
        val topicView: TextView = itemView.findViewById<TextView>(R.id.topicName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.feed_topic, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.topicView.text = mWordList[position].topic

        holder.entryView.setOnClickListener {
            val intent = Intent(it.context, FeedActivity::class.java)
            intent.putExtra("feed", mWordList[position])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mWordList.size
    }
}