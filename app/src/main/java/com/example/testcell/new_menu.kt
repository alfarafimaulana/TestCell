package com.example.testcell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.Navigation

import com.google.android.material.bottomnavigation.BottomNavigationView

class new_menu : AppCompatActivity() {
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_menu)
        supportActionBar?.hide()

        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navMap : FragmentContainerView = findViewById(R.id.newMenu)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    if (page != 1){
                        if (page == 2){
                            Navigation.findNavController(navMap).navigate(R.id.action_newProfile_to_newMainMenu)
                            page = 1
                        }
                        if (page == 3){
                            Navigation.findNavController(navMap).navigate(R.id.action_voiceOfC_to_newMainMenu)
                            page = 1
                        }
                    }
                }
                R.id.page_2 -> {
                    if (page != 2){
                        if (page == 1){
                            Navigation.findNavController(navMap).navigate(R.id.action_newMainMenu_to_newProfile)
                            page = 2
                        }
                        if (page == 3){
                            Navigation.findNavController(navMap).navigate(R.id.action_voiceOfC_to_newProfile)
                            page = 2
                        }
                    }
                }
                R.id.page_3 -> {
                    if (page != 3){
                        if (page == 1){
                            Navigation.findNavController(navMap).navigate(R.id.action_newMainMenu_to_voiceOfC)
                            page = 3
                        }
                        if (page == 2){
                            Navigation.findNavController(navMap).navigate(R.id.action_newProfile_to_voiceOfC)
                            page = 3
                        }
                    }
                }
            }
            true
        }




    }
    override fun onBackPressed() {
        val navMap : FragmentContainerView = findViewById(R.id.newMenu)
        if (page != 1){
            if (page == 2){
                Navigation.findNavController(navMap).navigate(R.id.action_newProfile_to_newMainMenu)
                page = 1
            }
            if (page == 3){
                Navigation.findNavController(navMap).navigate(R.id.action_voiceOfC_to_newMainMenu)
                page = 1
            }
        }
        else{
            finish()
        }
    }
    }
