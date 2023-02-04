package com.moviles.firebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.get
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.moviles.firebase.R
import com.moviles.firebase.databinding.FragmentAddBinding
import com.moviles.firebase.model.User


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    //DB
    private lateinit var dataBase: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
        // return inflater.inflate(R.layout.fragment_add, container, false)
    }


    private fun init() {
        //Escucha botón
        binding.buttonRegister.setOnClickListener {
            //Obtención de datos

            val userName: String = binding.userNameEditable.text.toString()
            val lastName: String = binding.lastNameEdit.text.toString()
            val firsName: String = binding.firstNameEdit.text.toString()
            val age: String = binding.ageEdit.text.toString()
            val user = User(firsName, lastName, userName, age)
            println(user.username)
            println(user)
            //Verificar si es nulo o empty
            if (firsName.isNullOrEmpty()) {
                Toast.makeText(
                    activity?.baseContext,
                    "Por favor rellene el campo de nombre ",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (lastName.isNullOrEmpty()) {
                Toast.makeText(
                    activity?.baseContext,
                    "Por favor rellene el campo de apellido",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (userName.isNullOrEmpty()) {
                Toast.makeText(
                    activity?.baseContext,
                    "Por favor rellene el campo de nombre de usuario",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (age.isNullOrEmpty()) {
                Toast.makeText(
                    activity?.baseContext,
                    "Por favor rellene el campo de edad",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            addUser(user)
        }
    }

    override fun onStart() {
        super.onStart()
        binding.buttonRegister.setOnClickListener {
            println("hallo")
        }
        init()
    }

    private fun addUser(user: User) {
        //Instancia
        dataBase = FirebaseDatabase.getInstance().getReference("Users")
        dataBase.child(user.username).setValue(user).addOnSuccessListener {
            binding.ageEdit.text?.clear()
            binding.userNameEditable.text?.clear()
            binding.firstNameEdit.text?.clear()
            binding.lastNameEdit.text?.clear()
            Toast.makeText(
                activity?.baseContext,
                "Usuario agregado exitosamente",
                Toast.LENGTH_SHORT
            ).show()
        }.addOnFailureListener {
            Toast.makeText(
                activity?.baseContext,
                "Falla al tratar de agregar al usuario",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}