package com.moviles.firebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.moviles.firebase.R
import com.moviles.firebase.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    private lateinit var database : DatabaseReference
    private lateinit var binding: FragmentUpdateBinding
    override fun onStart() {
        super.onStart()
        data()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }
    private fun data(){
        binding.buttonUpdate.setOnClickListener {
            val userName: String = binding.userNameEdit.text.toString()
            val lastName: String = binding.lastNameEdit.text.toString()
            val firstName: String = binding.firstNameEdit.text.toString()
            val age: String = binding.ageEdit.text.toString()

            updateData(userName,firstName,lastName,age)
        }
    }
    private fun updateData(userName: String, firstName: String, lastName: String, age: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf<String,String>(
            "firstName" to firstName,
            "lastName" to lastName,
            "age" to age,
            "username" to userName
        )


        database.child(userName).updateChildren(user).addOnSuccessListener {
            binding.userName.editText!!.text.clear()
            binding.firstName.editText!!.text.clear()
            binding.lastName.editText!!.text.clear()
            binding.age.editText!!.text.clear()
            Toast.makeText(context,"Successfuly Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(context,"Failed to Update", Toast.LENGTH_SHORT).show()
        }}
}