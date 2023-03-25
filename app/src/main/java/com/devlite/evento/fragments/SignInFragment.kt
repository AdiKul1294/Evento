package com.devlite.evento.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.devlite.evento.R
import com.devlite.evento.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class SignInFragment : Fragment() {

    var mProgressDialog : Dialog? = null

    private lateinit var email:String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = DataBindingUtil.inflate<FragmentSignInBinding>(inflater,R.layout.fragment_sign_in,container,false)

        binding.btnSignInSignIn.setOnClickListener {
            email=binding.tietEmailSignIn.text.toString()
            password=binding.tietPasswordSignIn.text.toString()
            if(validateForm(email,password)){
                signInUser(email,password)
            }

        }

        return binding.root
    }

    private fun signInUser(email: String, password: String){

        showProgressDialog("Loading")
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {      //This is lamda function
            hideProgressDialog()                                                                                        //Know how to make it a regular one
            if (it.isSuccessful) {
                val firebaseUser = it.result!!.user!!
                Toast.makeText(
                    context,
                    "${firebaseUser.email} you have successfully logged in",
                    Toast.LENGTH_SHORT
                ).show()
                Navigation.findNavController(requireView()).navigate(R.id.action_global_nav_graph_home)

            } else {
                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(email: String, password: String): Boolean{

        if(email.isEmpty()){
            showError("Please enter a valid email")
            return true
        }
        if(password.isEmpty()){
            showError("Please enter a valid password")
            return false
        }
        return true

    }

    fun showError(message: String){
        val req = activity?.findViewById<View>(android.R.id.content)
        if(req !=null) {
            val snackBar = Snackbar.make(req, message, Snackbar.LENGTH_LONG)
            context?.let {
                ContextCompat.getColor(
                    it,
                    R.color.purple_500
                )
            }?.let {
                snackBar.view.setBackgroundColor(
                    it
                )
            }
            snackBar.setTextColor(resources.getColor(R.color.white) )
            snackBar.show()
        }
    }

    fun showProgressDialog(text: String){                                                         //WHAT IS THISSS??
        mProgressDialog = context?.let { Dialog(it) }
        mProgressDialog?.setContentView(R.layout.dialog_progress)
        mProgressDialog?.findViewById<TextView>(R.id.tv_progress_text)?.text = text
        mProgressDialog?.show()
    }

    fun hideProgressDialog() {
        mProgressDialog?.dismiss()
    }

}