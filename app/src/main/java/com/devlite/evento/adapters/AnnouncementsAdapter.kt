package com.devlite.evento.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devlite.evento.R
import com.devlite.evento.data_classes.Announcement

class AnnouncementsAdapter (val announcements: List<Announcement>): RecyclerView.Adapter<AnnouncementsAdapter.AnnouncementViewHolder>() {

    inner class AnnouncementViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event, parent, false)
        return AnnouncementViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.title_text_view).text = announcements[position].title
            findViewById<TextView>(R.id.author_text_view).text = announcements[position].by
            findViewById<TextView>(R.id.message_text_view).text = announcements[position].message
            findViewById<TextView>(R.id.date_time_text_view).text =
                announcements[position].time+ " " + announcements[position].date

        }
    }

    override fun getItemCount(): Int {
        return announcements.size
    }
}