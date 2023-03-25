package com.devlite.evento.fragments.agenda

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.devlite.evento.R
import com.devlite.evento.adapters.AgendaAdapter
import com.devlite.evento.databinding.FragmentAgendaDay1Binding
import com.devlite.evento.data_classes.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AgendaDay1Fragment : Fragment() {
    private lateinit var binding: FragmentAgendaDay1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agenda_day1, container, false)

        setupRecyclerView()

        return binding.root
    }

    fun setupRecyclerView() {
        val fAuth = FirebaseAuth.getInstance()
        val fStore = FirebaseFirestore.getInstance()
        val userId = fAuth.currentUser!!.uid
        val cRef =
            fStore.collection("users").document(userId).collection("agendas").document("day1")
                .collection("day1")

        cRef.get()
            .addOnSuccessListener { documents ->
                val events = mutableListOf<Event>()
                for (document in documents) {
                    val event = Event(
                        document.id,
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
                            events.add(
                                i,
                                Event("${i + 100}", events[i].time, "", "", events[i].time, true)
                            )
                            size++
                        }
                        i++
                    }

                    val adapter = AgendaAdapter(events)
                    binding.agendaD1RvAd1f.apply {
                        this.adapter = adapter
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                    adapter.onItemClick = {
                        cRef.document(it.eid).delete()
                            .addOnSuccessListener { Log.d("Delete", "DocumentSnapshot successfully deleted!")
                                Toast.makeText(
                                    context,
                                    "Event removed from Agenda",
                                    Toast.LENGTH_SHORT
                                ).show()}
                            .addOnFailureListener { e -> Log.w("Delete", "Error deleting document", e) }
                        val handler = Handler()
                        handler.postDelayed({
                            setupRecyclerView()
                        }, 3000)
                    }
                }
            }
    }
}