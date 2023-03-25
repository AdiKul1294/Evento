package com.devlite.evento.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.devlite.evento.R
import com.devlite.evento.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,R.layout.fragment_home,container,false)



//        binding.tbHomeFrag.inflateMenu(R.menu.home_frag_menu)
//        binding.tbHomeFrag.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.user_btn -> {
//                    val navCont = findNavController()
//                    navCont.navigate(R.id.loginOrSignUpFragment)
//                    true
//                }
//
//                else -> false
//            }
//        }

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