package com.moviles.firebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moviles.firebase.R
import com.moviles.firebase.databinding.FragmentAddBinding




class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
       // return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onStart() {
        super.onStart()
        binding.buttonRegister.setOnClickListener{
            println("hallo")
        }
    }

}