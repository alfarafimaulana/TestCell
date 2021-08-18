package com.example.testcell

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class bugReportAct : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bug_report)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()



        model()


        val btn : Button = findViewById(R.id.tombolReport)
        btn.setOnClickListener{
            savedata()
            saveFirestore()
        }

    }

    fun model() {
        val manu = Build.MANUFACTURER
        val model = Build.MODEL
        val build = "$manu $model"

        val devM : TextView = findViewById(R.id.deviceMBugReport)
        devM.text = build
    }
    fun savedata(){
        val mod : TextView = findViewById(R.id.deviceMBugReport)
        val kom : EditText = findViewById(R.id.laporanMasalah)



        database = FirebaseDatabase.getInstance().getReference("bugReport")


        val dataid = database.push().key
        val model = mod.text.toString()
        val complain = kom.text.toString()

        val bug = dataReport(dataid.toString(),model,complain)
        database.child(dataid.toString()).setValue(bug).addOnSuccessListener {
            //Toast.makeText(this, "Terima kasih atas masukannya", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            //Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
    fun saveFirestore(){
        val mod : TextView = findViewById(R.id.deviceMBugReport)
        val kom : EditText = findViewById(R.id.laporanMasalah)

        val currentUser = mAuth.currentUser
        val userEmail = currentUser?.email

        val model = mod.text.toString()
        val complain = kom.text.toString()


        val user = hashMapOf(
            "User Email" to "$userEmail",
            "Phone Model" to "$model",
            "Complain" to "$complain",
            "Complain and Model" to "$model: $complain",

        )

// Add a new document with a generated ID
        db.collection("Komplain").add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Terima kasih atas masukannya", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

    }



}

