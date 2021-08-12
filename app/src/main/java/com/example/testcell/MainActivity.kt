package com.example.testcell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        val namaUser : TextView = findViewById(R.id.namaUser)
        val gambarUser : ImageView = findViewById(R.id.gambarUser)

        //id_txt.text = currentUser?.uid
        namaUser.text = currentUser?.displayName
        //email_txt.text = currentUser?.email

        Glide.with(this).load(currentUser?.photoUrl).into(gambarUser)
        timeKnown()


        gambarUser.setOnClickListener {
            val intent = Intent(this,profileUser::class.java)
            startActivity(intent)
        }

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
        var timeOfDay = timeSecond().toInt()
        val timep : TextView = findViewById(R.id.waktuText)
        if(timeOfDay >= 0 && timeOfDay < 11){
            timep.text = "Selamat Pagi"
        }else if(timeOfDay >= 11 && timeOfDay < 17){
            timep.text = "Selamat Siang"
        }else if(timeOfDay >= 17 && timeOfDay < 20){
            timep.text = "Selamat Sore"
        }else if(timeOfDay >= 20 && timeOfDay < 24){
            timep.text = "Selamat Malam"
        }
    }
    fun timeSecond(): String {
        val dateFormat = SimpleDateFormat("HH", Locale.US)
        return dateFormat.format(Date())
    }
}