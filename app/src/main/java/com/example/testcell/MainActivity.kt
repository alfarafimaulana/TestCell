package com.example.testcell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        val test : ImageView = findViewById(R.id.testImage)
        test.setOnClickListener{
            val intent = Intent(this,TestAct::class.java)
            startActivity(intent)
        }

        val opini : ImageView = findViewById(R.id.opiniImage)
        opini.setOnClickListener{
            val intent = Intent(this,opiniAct::class.java)
            startActivity(intent)
        }

        val bug : FloatingActionButton = findViewById(R.id.bugFloating)
        bug.setOnClickListener{
            val intent = Intent(this,bugReportAct::class.java)
            startActivity(intent)
        }
    }
}