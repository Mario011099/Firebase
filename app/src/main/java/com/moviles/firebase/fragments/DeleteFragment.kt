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
import com.moviles.firebase.databinding.FragmentDeleteBinding

class DeleteFragment : Fragment() {

    private lateinit var binding: FragmentDeleteBinding
    //DB
    private lateinit var dataBase: DatabaseReference
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeleteBinding.inflate(inflater,container,false)
        return binding.root
    }
    
    private fun init(){
        //Escucha botón
        binding.buttonDelete.setOnClickListener {
            //Obtención de datos
            val userName = binding.nameUser.text.toString()
            //Verificar si es nulo o empty
            if(!userName.isNullOrEmpty()){
                deleteUser(userName)
            }else{
                Toast.makeText(activity?.baseContext,
                "Por favor rellene el campo de nombre de usuario",
                Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteUser(userName: String) {
        //Instancia
        dataBase = FirebaseDatabase.getInstance().getReference("Users")
        dataBase.child(userName).removeValue().addOnSuccessListener {
            //Eliminar valores de campo
            binding.nameUser.text?.clear()
            //Mensaje
            Toast.makeText(activity?.baseContext,
                "Usuario eliminado exitosamente",
                Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(activity?.baseContext,
                "Falla al tratar de eliminar al usuario",
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        init()
    }
}