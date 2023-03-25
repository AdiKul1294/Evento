package com.devlite.evento.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.devlite.evento.R
import com.devlite.evento.databinding.FragmentLoginOrSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class LoginOrSignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentLoginOrSignUpBinding>(inflater,R.layout.fragment_login_or_sign_up,container,false)

        if( FirebaseAuth.getInstance().currentUser?.uid != null){
            view?.let {
                Navigation.findNavController(it).navigate(R.id.action_global_nav_pass)
            }
        }

        binding.btnSignUp.setOnClickListener{
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_loginOrSignUpFragment_to_signUpFragment) }
        }
        binding.btnSignIn.setOnClickListener{
            view?.let {
                Navigation.findNavController(it).navigate(R.id.action_nav_login_log_out_to_signInFragment)
            }
        }
        return binding.root
    }

}