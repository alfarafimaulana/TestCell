package com.example.testcell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.Navigation

import com.google.android.material.bottomnavigation.BottomNavigationView

class new_menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_menu)
        supportActionBar?.hide()

        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navMap : FragmentContainerView = findViewById(R.id.fragmentMenu)


        bottomNavigation.setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    Navigation.findNavController(navMap).navigate(R.id.action_newProfile_to_newMainMenu)

                }
                R.id.page_2 -> {
                    Navigation.findNavController(navMap).navigate(R.id.action_newMainMenu_to_newProfile)
                }
            }
        }
    }
}