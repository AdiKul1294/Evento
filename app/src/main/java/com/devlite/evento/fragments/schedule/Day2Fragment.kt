package com.devlite.evento.fragments.schedule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.devlite.evento.R
import com.devlite.evento.adapters.EventsAdapter
import com.devlite.evento.databinding.FragmentDay2Binding
import com.devlite.evento.data_classes.Event
import com.google.firebase.firestore.FirebaseFirestore

class Day2Fragment : Fragment() {

    private lateinit var binding: FragmentDay2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day2, container, false)

        val events = mutableListOf<Event>()
        val fStore = FirebaseFirestore.getInstance()
        val cRef = fStore.collection("schedule").document("day2").collection("day2")

        cRef
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val event = Event(
                        document.get("eid").toString(),
                        document.get("title").toString(),
                        document.get("location").toString(),
                        document.get("desc").toString(),
                        document.get("time").toString(),
                        false
                    )
                    events.add(event)
                }

                if (events.isNotEmpty()) {
                    events.add(0, Event("100", events[0].time, "", "", events[0].time, true))
                    var size = events.size

                    var i = 1
                    while (i < size) {
                        if (events[i].time != events[i - 1].time) {
                            events.add(i, Event("${i + 100}", events[i].time, "", "", events[i].time, true))
                            size++
                        }
                        i++
                    }

                    val adapter = EventsAdapter(events)
                    binding.eventsRvD2f.apply {
                        this.adapter = adapter
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Document Fetch", "Error getting documents: ", exception)
            }
        return binding.root
    }
}