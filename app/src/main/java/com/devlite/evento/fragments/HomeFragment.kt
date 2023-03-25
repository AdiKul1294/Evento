package com.devlite.evento.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.devlite.evento.R
import com.devlite.evento.adapters.AnnouncementsAdapter
import com.devlite.evento.adapters.EventsAdapter
import com.devlite.evento.data_classes.Announcement
import com.devlite.evento.databinding.FragmentHomeBinding
import com.devlite.evento.data_classes.Event
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
                    binding.rvAnnouncements.adapter = AnnouncementsAdapter(announcements)
                    binding.rvAnnouncements.layoutManager = LinearLayoutManager(context)
                }
            }

        if(currUser!= null) {
            FirebaseFirestore.getInstance().collection("users").document(currUser.uid)
                .collection("day1")         //gets the latest data and puts it in rv
                .get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        var day1Schedule = it.result.toObjects<Event>().toMutableList()
                        binding.rvAgendaNear.adapter = EventsAdapter(day1Schedule)
                        binding.rvAgendaNear.layoutManager = LinearLayoutManager(context)
                    }
                }
        }


        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {

//        val efb_login = activity?.findViewById<ExtendedFloatingActionButton>(R.id.efb_login)
//
//        efb_login?.setOnClickListener{
//            val navController = findNavController()
//            navController.navigate(R.id.btnSignUpSignUpFrag)
//        }

        super.onCreate(savedInstanceState)
    }

}