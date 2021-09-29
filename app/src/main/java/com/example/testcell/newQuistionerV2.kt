package com.example.testcell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class newQuistionerV2 : AppCompatActivity() {

    var kodePos = ""
    var penghasilan = ""
    var xBelanja = ""
    var uangBelanja = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_quistioner_v2)
        supportActionBar?.hide()

        val kodePosText : EditText = findViewById(R.id.kodePosText)

        val box260juta : CheckBox = findViewById(R.id.cBox260juta)
        val box2Sampai : CheckBox = findViewById(R.id.cBox2Sampai)
        val box2120juta : CheckBox = findViewById(R.id.cBox2120juta)

        val box32kali : CheckBox = findViewById(R.id.cBox32kali)
        val box3Sampai : CheckBox = findViewById(R.id.cBox3Sampai)
        val box35kali : CheckBox = findViewById(R.id.cBox35kali)

        val box42juta : CheckBox = findViewById(R.id.cBox42juta)
        val box4Sampai : CheckBox = findViewById(R.id.cBox4Sampai)
        val box45juta : CheckBox = findViewById(R.id.cBox45juta)

        val submit : Button = findViewById(R.id.sumbitButton)


        box260juta.setOnClickListener {
            box2Sampai.setChecked(false)
            box2120juta.setChecked(false)

            box2Sampai.setEnabled(true)
            box2120juta.setEnabled(true)

            penghasilan = "dibawah 60 juta"
            Toast.makeText(this, penghasilan.toString(), Toast.LENGTH_SHORT).show()
            box260juta.setEnabled(false)
        }

        box2Sampai.setOnClickListener {
            box260juta.setChecked(false)
            box2120juta.setChecked(false)

            box260juta.setEnabled(true)
            box2120juta.setEnabled(true)

            penghasilan = "60 - 120 juta"
            Toast.makeText(this, penghasilan.toString(), Toast.LENGTH_SHORT).show()
            box2Sampai.setEnabled(false)
        }

        box2120juta.setOnClickListener {
            box260juta.setChecked(false)
            box2Sampai.setChecked(false)

            box260juta.setEnabled(true)
            box2Sampai.setEnabled(true)

            penghasilan = "diatas 120 juta"
            Toast.makeText(this, penghasilan.toString(), Toast.LENGTH_SHORT).show()
            box2120juta.setEnabled(false)
        }

        box32kali.setOnClickListener {
            box3Sampai.setChecked(false)
            box35kali.setChecked(false)

            box3Sampai.setEnabled(true)
            box35kali.setEnabled(true)

            xBelanja = "dibawah 2 kali"
            Toast.makeText(this, xBelanja.toString(), Toast.LENGTH_SHORT).show()
            box32kali.setEnabled(false)
        }

        box3Sampai.setOnClickListener {
            box32kali.setChecked(false)
            box35kali.setChecked(false)

            box32kali.setEnabled(true)
            box35kali.setEnabled(true)

            xBelanja = "2 - 5 kali"
            Toast.makeText(this, xBelanja.toString(), Toast.LENGTH_SHORT).show()
            box3Sampai.setEnabled(false)
        }

        box35kali.setOnClickListener {
            box32kali.setChecked(false)
            box3Sampai.setChecked(false)

            box32kali.setEnabled(true)
            box3Sampai.setEnabled(true)

            xBelanja = "diatas 5 kali"
            Toast.makeText(this, xBelanja.toString(), Toast.LENGTH_SHORT).show()
            box35kali.setEnabled(false)
        }

        box42juta.setOnClickListener {
            box4Sampai.setChecked(false)
            box45juta.setChecked(false)

            box4Sampai.setEnabled(true)
            box45juta.setEnabled(true)

            uangBelanja = "dibawah 2 juta rupiah"
            Toast.makeText(this, uangBelanja.toString(), Toast.LENGTH_SHORT).show()
            box42juta.setEnabled(false)
        }

        box4Sampai.setOnClickListener {
            box42juta.setChecked(false)
            box45juta.setChecked(false)

            box42juta.setEnabled(true)
            box45juta.setEnabled(true)

            uangBelanja = "2 - 5 juta"
            Toast.makeText(this, uangBelanja.toString(), Toast.LENGTH_SHORT).show()
            box4Sampai.setEnabled(false)
        }

        box45juta.setOnClickListener {
            box42juta.setChecked(false)
            box4Sampai.setChecked(false)

            box42juta.setEnabled(true)
            box4Sampai.setEnabled(true)

            uangBelanja = "diatas 5 juta rupiah"
            Toast.makeText(this, uangBelanja.toString(), Toast.LENGTH_SHORT).show()
            box45juta.setEnabled(false)
        }


        submit.setOnClickListener {
            kodePos = kodePosText.toString()
            val database = FirebaseDatabase.getInstance().getReference("data")




            if (kodePos.trim().isEmpty() && penghasilan.trim().isEmpty() && xBelanja.trim().isEmpty() && !uangBelanja.trim().isEmpty()){
                Toast.makeText(this, "Harap mengisi semua pertanyaan", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()


            }




            }


//        database = FirebaseDatabase.getInstance().getReference("data")
//        var mode = 2
//        if (mode == 1) {

//            val dataid = database.push().key
//            val TimeStamp = timeStamp.text.toString()
//            val Operator = operator.text.toString()
//            val Model = buildModel.text.toString()
//            val Longitude = long.text.toString()
//            val Latitude = lat.text.toString()
//            val Signal = signalStr.text.toString()
//            val Latency = late.text.toString()
//            val UploadSpeed = uploadView.text.toString()
//            val DownloadSpeed = downloadView.text.toString()
//
//            val data = Data(dataid.toString(),TimeStamp,Operator,Model,Longitude,Latitude,Signal,Latency,UploadSpeed,DownloadSpeed)
//            database.child(dataid.toString()).setValue(data).addOnSuccessListener {
//                Toast.makeText(this, "Terima kasih atas masukannya",Toast.LENGTH_SHORT).show()
//            }.addOnFailureListener {
//                Toast.makeText(this, "Failed",Toast.LENGTH_SHORT).show()}




























//        var gajiVal = ""
//        val gajiList = listOf("dibawah 60 juta rupiah","60 - 120 juta rupiah","diatas 120 juta rupiah")
//        val adapterGaji = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,gajiList)

//        val spGaji : Spinner = findViewById(R.id.spGaji)
//        spGaji.adapter = adapterGaji
//        spGaji.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                gajiVal = parent?.getItemAtPosition(position).toString()
//                if(gajiVal == "dibawah 60 juta rupiah"){
//                    gajiVal = " < 60 Jt "
//                }
//                if(gajiVal == "60 - 120 juta rupiah"){
//                    gajiVal = " Rp 60 - 120 Jt "
//                }
//                if(gajiVal == "diatas 120 juta rupiah"){
//                    gajiVal = " > 120 Jt "
//                }
//
//
//                Toast.makeText(this@newQuistionerV2,"this ${gajiVal}",Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//
//        }






        
    }
}