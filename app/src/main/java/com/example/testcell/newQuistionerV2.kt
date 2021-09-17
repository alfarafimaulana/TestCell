package com.example.testcell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class newQuistionerV2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_quistioner_v2)
        supportActionBar?.hide()

        var gajiVal = ""
        val gajiList = listOf("dibawah 60 juta rupiah","60 - 120 juta rupiah","diatas 120 juta rupiah")
        val adapterGaji = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,gajiList)
        val spGaji : Spinner = findViewById(R.id.spGaji)
        spGaji.adapter = adapterGaji


        spGaji.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                gajiVal = parent?.getItemAtPosition(position).toString()
                if(gajiVal == "dibawah 60 juta rupiah"){
                    gajiVal = " < 60 Jt "
                }
                if(gajiVal == "60 - 120 juta rupiah"){
                    gajiVal = " Rp 60 - 120 Jt "
                }
                if(gajiVal == "diatas 120 juta rupiah"){
                    gajiVal = " > 120 Jt "
                }


                Toast.makeText(this@newQuistionerV2,"this ${gajiVal}",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        
    }
}