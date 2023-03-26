package com.devlite.evento.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.devlite.evento.R
import com.devlite.evento.databinding.FragmentPassBinding
import com.google.firebase.auth.FirebaseAuth

class PassFragment : Fragment() {
    private lateinit var binding: FragmentPassBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pass, container, false)

        binding.tvPassTitle.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in))
        binding.tvEvents.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in))

        return binding.root
    }

    override fun onStart() {

        if(FirebaseAuth.getInstance().currentUser != null) {
            binding.tvUserMail.text = FirebaseAuth.getInstance().currentUser!!.email
            binding.efabLogout.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(context,"Signed out successfully", Toast.LENGTH_SHORT).show()
                view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.nav_graph_login)}
            }

        }
        else{
            binding.tvPassTitle.text=""
            binding.tvEvents.text = ""
            binding.tvUserMail.text = "Login to continue"
            binding.efabLogout.text = "Login"
            binding.efabLogout.icon = context?.resources?.getDrawable(R.drawable.ic_baseline_login_24)
            binding.efabLogout.setOnClickListener {
                view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.nav_graph_login) }
            }
        }

        super.onStart()
    }

}