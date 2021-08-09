package com.example.testcell

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class bugReportAct : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bug_report)
        supportActionBar?.hide()
        model()


        val btn : Button = findViewById(R.id.tombolReport)
        btn.setOnClickListener{
            savedata()
        }

    }

    fun model() {
        var manu = Build.MANUFACTURER
        var model = Build.MODEL
        var build = "$manu $model"

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
            Toast.makeText(this, "Terima kasih atas masukannya", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}

