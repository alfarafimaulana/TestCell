package com.example.testcell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView

class profileUser : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        val namaUser : TextView = findViewById(R.id.namaUs)

        namaUser.text = currentUser?.displayName
        val gambarUserProfile : CircleImageView = findViewById(R.id.gambarUserProfile)
        Glide.with(this).load(currentUser?.photoUrl).into(gambarUserProfile)


        val signOut : Button = findViewById(R.id.signOut)
        signOut.setOnClickListener{
            mAuth.signOut()
            val intent = Intent(this, signInAct::class.java)
            startActivity(intent)
            finish()
        }

    }


}