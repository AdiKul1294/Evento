package com.devlite.evento.fragments.agenda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.devlite.evento.R
import com.devlite.evento.databinding.FragmentAgendaDay2Binding

class AgendaDay2Fragment : Fragment() {
    private lateinit var binding: FragmentAgendaDay2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agenda_day2, container, false)

        return binding.root
    }
}