package com.moviles.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.moviles.firebase.databinding.ActivityMainBinding
import com.moviles.firebase.fragments.AddFragment
import com.moviles.firebase.fragments.DeleteFragment
import com.moviles.firebase.fragments.SearchFragment
import com.moviles.firebase.fragments.UpdateFragment

class MainActivity : AppCompatActivity() {
    //lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
       // setContentView(binding.root)
        setContentView(R.layout.activity_main)
        fragmentVisibility(AddFragment())
        initListeners()
    }
    private fun fragmentVisibility(fragment: Fragment) {
        var FragmentPrincipal = findViewById<FrameLayout>(R.id.frame)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(FragmentPrincipal.id, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
    fun initListeners(){
        var menu = findViewById<TabLayout>(R.id.menu)

        menu.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {

                if(tab?.contentDescription == "add"){
                    fragmentVisibility(AddFragment())
                } else if (tab?.contentDescription == "search"){
                    fragmentVisibility(SearchFragment())
                } else if (tab?.contentDescription == "delete"){
                    fragmentVisibility(DeleteFragment())
                } else if (tab?.contentDescription == "update"){
                    fragmentVisibility(UpdateFragment())
                } else {
                    //
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }


        })
    }
}