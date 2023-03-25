package com.devlite.evento.fragments.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.devlite.evento.R
import com.devlite.evento.adapters.EventsAdapter
import com.devlite.evento.databinding.FragmentDay3Binding
import com.devlite.evento.dataclasses.Event

class Day3Fragment : Fragment() {

    private lateinit var binding: FragmentDay3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day3, container, false)

        val events = mutableListOf(
            Event("Code by the Beach",
                "LT-2",
                "Hackathon",
                "9:00 am",
                false),
            Event("Code by the Beach",
                "LT-2",
                "Hackathon",
                "9:00 am",
                false),
            Event("Code by the Beach",
                "LT-2",
                "Hackathon",
                "10:00 am",
                false),
            Event("Code by the Beach",
                "LT-2",
                "Hackathon",
                "11:00 am",
                false),
            Event("Code by the Beach",
                "LT-2",
                "Hackathon",
                "12:00 pm",
                false),
            Event("Code by the Beach",
                "LT-2",
                "Hackathon",
                "1:00 pm",
                false),
            Event("Code by the Beach",
                "LT-2",
                "Hackathon",
                "1:00 pm",
                false)
        )
        events.add(0, Event(events[0].time, "", "", events[0].time, true))
        var size = events.size

        var i=1
        while(i<size){
            if(events[i].time!=events[i-1].time){
                events.add(i, Event(events[i].time, "", "", events[i].time, true))
                size++
            }
            i++
        }
        val adapter = EventsAdapter(events)
        binding.eventsRvD3f.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        return binding.root
    }

}