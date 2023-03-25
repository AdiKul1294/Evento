package com.devlite.evento.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
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
import com.devlite.evento.data_classes.UserApna
import com.devlite.evento.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SignUpFragment : Fragment() {

    var mProgressDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("Auth","Sign up fragment reached")

        val binding = DataBindingUtil.inflate<FragmentSignUpBinding>(inflater,R.layout.fragment_sign_up,container,false)
        binding.btnSignUpSignUpFrag.setOnClickListener {
            Log.d("Auth","Sign up button presses")
            val name = binding.tietNameSignUp.text.toString()
            val email =binding.tietEmailSignUp.text.toString()
            val password = binding.tietPasswordSignUp.text.toString()
            val college = binding.tietCollegeName.text.toString()
            val collegePlace = binding.tietCollegePlace.text.toString()
            val phoneNo = binding.tietPhoneNo.text.toString()
            if(validateForm(name, email, password,college,collegePlace,phoneNo)){
                signUpUser(name,email,password,college, collegePlace,phoneNo)
                Navigation.findNavController(requireView()).navigate(R.id.action_global_nav_graph_home)
            }
        }

        return binding.root
    }

    private fun validateForm(name:String, email:String, password:String,college:String,collegePlace:String,phoneNo:String): Boolean{

        if(name.length == 0){
            showError("Please enter a valid email")
            return true
        }

        if(email.length == 0){
            showError("Please enter a valid email")
            return true
        }
        if(password.length == 0){
            showError("Please enter a valid password")
            return false
        }
        if(college.length <1){
            showError("Invalid College name")
            return false
        }
        if(collegePlace.length < 1){
            showError("Invalid college place")
            return false
        }
        if(phoneNo.length != 10){
            return false
        }
        return true

    }


    fun signUpUser(name:String, email:String, password:String,college:String,collegePlace:String,phoneNo:String) {

        showProgressDialog(getString(R.string.requesting_patience))
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {      //This is lamda function
                hideProgressDialog()                                                                                        //Know how to make it a regular one
                if (it.isSuccessful) {
                    val firebaseUser = it.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    Toast.makeText(
                        context,
                        "${firebaseUser.email} you have successfully signed un",
                        Toast.LENGTH_SHORT
                    ).show()

                    val currUser = UserApna(firebaseUser.uid,name, email,password, college, collegePlace, phoneNo)

                    FirebaseFirestore.getInstance().collection("users")
                        .document(firebaseUser.uid).set(currUser).addOnSuccessListener {
                            Toast.makeText(context,"Added successfully", Toast.LENGTH_SHORT).show()
                        }
                    Log.d("Auth","Signup successful")
                }

                else{
                    Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    Log.d("Auth","Signin failed")
                }
            }
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