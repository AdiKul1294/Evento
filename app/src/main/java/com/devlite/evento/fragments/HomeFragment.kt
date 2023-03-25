package com.devlite.evento.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devlite.evento.R
import com.devlite.evento.adapters.AnnouncementsAdapter
import com.devlite.evento.adapters.EventsAdapter
import com.devlite.evento.adapters.NearAgendaAdapter
import com.devlite.evento.data_classes.Announcement
import com.devlite.evento.databinding.FragmentHomeBinding
import com.devlite.evento.dataclasses.Event
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,R.layout.fragment_home,container,false)
        val currUser = FirebaseAuth.getInstance().currentUser

        FirebaseFirestore.getInstance().collection("announcements")         //gets the latest data and puts it in rv
            .get().addOnCompleteListener {
                if (it.isSuccessful) {

                    val announcements = it.result.toObjects<Announcement>().toMutableList()
                    if( !announcements.isEmpty()) {
                        binding.rvAnnouncements.adapter = AnnouncementsAdapter(announcements)
                        binding.rvAnnouncements.layoutManager = LinearLayoutManager(context)
                        Log.d("Home frag","announcement not empty")
                    }
                    else{
                        announcements.add( Announcement("","","","No announcements",""))
                        binding.rvAnnouncements.adapter = AnnouncementsAdapter(announcements)
                        binding.rvAnnouncements.layoutManager = LinearLayoutManager(context)
                        Log.d("Home Frag","Announcements empty")
                    }
                }
            }

        if(currUser!= null) {
            var events = mutableListOf<Event>()
            FirebaseFirestore.getInstance().collection("users").document(currUser.uid)
                .collection("agendas").document("day1").collection("day1")
                .get().addOnSuccessListener { documents ->
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
                    binding.rvAgendaNear.adapter = NearAgendaAdapter(events)
                    binding.rvAgendaNear.layoutManager = LinearLayoutManager(context)
                }
        }


        return binding.root

    }

}