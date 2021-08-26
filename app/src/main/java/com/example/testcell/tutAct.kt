package com.example.testcell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class tutAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tut)
        supportActionBar?.hide()
    }
}