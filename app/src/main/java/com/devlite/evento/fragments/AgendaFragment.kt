package com.devlite.evento.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.devlite.evento.R
import com.devlite.evento.databinding.FragmentAgendaBinding
import com.devlite.evento.dataclasses.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AgendaFragment : Fragment() {
    private lateinit var binding: FragmentAgendaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agenda, container, false)

        val fAuth = FirebaseAuth.getInstance()
        val fStore = FirebaseFirestore.getInstance()
        val userId = fAuth.currentUser!!.uid
        val cRef = fStore.collection("users").document(userId).collection("agendas").document("day1").collection("day1")


        cRef.get()
            .addOnSuccessListener { documents ->
                val events = mutableListOf<Event>()
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

                    val adapter = NearAgendaAdapter(events)
                    binding.agendaRvAf.apply {
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