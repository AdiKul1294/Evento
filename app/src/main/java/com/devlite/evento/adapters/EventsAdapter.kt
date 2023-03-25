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
import com.devlite.evento.data_classes.Event
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EventsAdapter(val events: List<Event>): RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event, parent, false)
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
                findViewById<FloatingActionButton>(R.id.add_fab).visibility = View.GONE
            }
            else {
                findViewById<TextView>(R.id.loc_tv).text = events[position].location
                findViewById<TextView>(R.id.desc_tv).text = events[position].desc
                findViewById<FloatingActionButton>(R.id.add_fab).setOnClickListener {
                    val fAuth = FirebaseAuth.getInstance()
                    val fStore = FirebaseFirestore.getInstance()
                    val userId = fAuth.currentUser!!.uid
                    val dRef = fStore.collection("users").document(userId).collection("agendas").document("day1").collection("day1").document(events[position].eid)
                    val event = hashMapOf<String, String>(
                        "title" to events[position].title,
                        "location" to events[position].location,
                        "desc" to events[position].desc,
                        "time" to events[position].time
                    )
                    dRef.set(event)

                    Toast.makeText(
                        context,
                        "Event added to Agenda",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}