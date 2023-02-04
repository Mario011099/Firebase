package com.moviles.firebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.moviles.firebase.R
import com.moviles.firebase.databinding.FragmentSearchBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.buttonLogin.setOnClickListener {
            val userName: String = binding.userNameField.text.toString()
            if (userName.isNotEmpty()) {
                readData(userName)
            } else {
                Toast.makeText(context, "Please enter Username", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun readData(userName: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        databae.child(userName).get().addOnSuccessListener {

            if (it.exists()) {
                val firstName = it.child("firstName").value
                val lastName = it.child("lastName").value
                val age = it.child("age").value
                Toast.makeText(context, "Successfuly Read", Toast.LENGTH_SHORT).show()
                binding.userNameField.text?.clear()
                binding.name.text = firstName.toString()
                binding.lastName.text = lastName.toString()
                binding.age.text = age.toString()

            } else {
                Toast.makeText(context, "Username doesn't exist", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}