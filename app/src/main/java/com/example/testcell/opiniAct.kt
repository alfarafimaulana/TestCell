package com.example.testcell

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class opiniAct : AppCompatActivity() {
    private var telephonyManager: TelephonyManager? = null
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opini)
        supportActionBar?.hide()
        telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        operator()

        val btnOp : Button = findViewById(R.id.tombolOp)
        btnOp.setOnClickListener{
            savedata()
        }

    }

    fun operator() {
        val operatorView: TextView = findViewById(R.id.operatorOp)
        val operatorName = telephonyManager?.networkOperatorName
        operatorView.text = operatorName.toString()
    }
    fun savedata(){
        val op : TextView = findViewById(R.id.operatorOp)
        val star : RatingBar = findViewById(R.id.ratingBarOp)
        val opDes : EditText = findViewById(R.id.opiniDes)



        database = FirebaseDatabase.getInstance().getReference("OperatorRating")


        val dataid = database.push().key
        val operator = op.text.toString()
        val rating = star.rating.toString()
        val opini = opDes.text.toString()

        val bug = dataOpini(dataid.toString(),operator,rating,opini)
        database.child(dataid.toString()).setValue(bug).addOnSuccessListener {
            Toast.makeText(this, "Terima kasih atas masukannya", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}