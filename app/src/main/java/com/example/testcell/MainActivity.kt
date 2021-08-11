package com.example.testcell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        timeKnown()

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

        val bug : ImageView = findViewById(R.id.bugFloating)
        bug.setOnClickListener{
            val intent = Intent(this,bugReportAct::class.java)
            startActivity(intent)
        }
    }

    fun timeKnown(){
        var timeOfDay = timeStamp().toInt()
        val timep : TextView = findViewById(R.id.waktuText)
        if(timeOfDay >= 0 && timeOfDay < 12){
            timep.text = "Selamat Pagi"
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            timep.text = "Selamat Siang"
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            timep.text = "Selamat Sore"
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            timep.text = "Selamat Malam"
        }
    }
    fun timeStamp(): String {
        val dateFormat = SimpleDateFormat("HH", Locale.US)
        return dateFormat.format(Date())
    }
}