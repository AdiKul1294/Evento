package com.devlite.evento.fragments.agenda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.devlite.evento.R
import com.devlite.evento.adapters.ViewPagerAdapter
import com.devlite.evento.databinding.FragmentAgendaBinding
import com.google.firebase.auth.FirebaseAuth

class AgendaFragment : Fragment() {
    private lateinit var binding: FragmentAgendaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agenda, container, false)
        return binding.root
    }

    override fun onStart() {
        if( FirebaseAuth.getInstance().currentUser == null){
            view?.let { Navigation.findNavController(it).navigate(R.id.nav_graph_login) }
        }

        setupTabs()
        super.onStart()
    }
    private fun setupTabs() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(AgendaDay1Fragment(), "Day 1")
        adapter.addFragment(AgendaDay2Fragment(), "Day 2")
        adapter.addFragment(AgendaDay3Fragment(), "Day 3")
        binding.viewPagerGf.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPagerGf)
    }
}