package com.example.testcell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
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
            Toast.makeText(this, "Coming Soon",Toast.LENGTH_SHORT).show()
        }

        val bug : FloatingActionButton = findViewById(R.id.bugFloating)
        bug.setOnClickListener{
            Toast.makeText(this, "Coming Soon",Toast.LENGTH_SHORT).show()
        }
    }
}