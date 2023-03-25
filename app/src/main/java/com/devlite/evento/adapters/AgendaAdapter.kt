package com.devlite.evento.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.devlite.evento.R
import com.devlite.evento.dataclasses.Event
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AgendaAdapter(val events: List<Event>): RecyclerView.Adapter<AgendaAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.agenda_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.title_tv).text = events[position].title
            findViewById<TextView>(R.id.loc_tv).text = events[position].location
            findViewById<TextView>(R.id.desc_tv).text = events[position].desc
            if(events[position].isHeader){
                findViewById<TextView>(R.id.title_tv).setTextColor(Color.BLUE)
                findViewById<TextView>(R.id.title_tv).textSize = 25f
                findViewById<TextView>(R.id.loc_tv).visibility = View.GONE
                findViewById<TextView>(R.id.desc_tv).visibility = View.GONE
                findViewById<ImageView>(R.id.loc_iv).visibility = View.GONE
                findViewById<ImageView>(R.id.tag_iv).visibility = View.GONE
            }
            else {
                findViewById<TextView>(R.id.loc_tv).text = events[position].location
                findViewById<TextView>(R.id.desc_tv).text = events[position].desc
            }
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}