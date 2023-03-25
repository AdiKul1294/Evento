package com.devlite.evento.fragments.agenda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.devlite.evento.R
import com.devlite.evento.adapters.ViewPagerAdapter
import com.devlite.evento.databinding.FragmentAgendaBinding

class AgendaFragment : Fragment() {
    private lateinit var binding: FragmentAgendaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agenda, container, false)

        setupTabs()

        return binding.root

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