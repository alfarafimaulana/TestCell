package com.example.testcell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class profileUser : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        val signOut : Button = findViewById(R.id.signOut)
        signOut.setOnClickListener{
            mAuth.signOut()
            val intent = Intent(this, signInAct::class.java)
            startActivity(intent)
            finish()
        }

    }
}