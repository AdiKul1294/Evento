package com.devlite.evento.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.devlite.evento.R
import com.devlite.evento.databinding.FragmentPassBinding

class PassFragment : Fragment() {
    private lateinit var binding: FragmentPassBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pass, container, false)

        binding.titleTvPf.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in))
        binding.eventsTvPf.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in))

        return binding.root
    }

}