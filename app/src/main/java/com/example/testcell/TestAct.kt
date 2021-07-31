package com.example.testcell

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*


class TestAct : AppCompatActivity() {
    private var telephonyManager: TelephonyManager? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    val RequestPermissionCode = 1
    var mLocation: Location? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient



    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_testone)





        val timeStamp: EditText = findViewById(R.id.timeStamp)
        val operator: EditText = findViewById(R.id.operator)
        val buildModel: EditText = findViewById(R.id.deviceModel)
        val long: EditText = findViewById(R.id.longitude)
        val lat: EditText = findViewById(R.id.latitude)
        val uploadView: EditText = findViewById(R.id.upload)
        val downloadView: EditText = findViewById(R.id.download)

        uploadView.setText("50")
        downloadView.setText("100")

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        operator.setText("refresh")

        val loc: Button = findViewById(R.id.loc2)
        loc.setOnClickListener{
            val longValue = long.text.toString()
            val latValue = lat .text.toString()
            viewLoc(latValue,longValue)
        }

        val refreshBtn: Button = findViewById(R.id.refresh2)
        refreshBtn.setOnClickListener {
            getLastLocation()
            timeStamp.setText(timeStamp())
            operator()
            model()


        }

        val speedBtn: Button = findViewById(R.id.speed2)
        speedBtn.setOnClickListener {

        }


        val sumbitBtn: Button = findViewById(R.id.submitData2)
        sumbitBtn.setOnClickListener {
            savedata()
        }

    }

    fun timeStamp(): String {
        val dateFormat = SimpleDateFormat("dd/mm/yyyy HH:mm:ss", Locale.US)
        return dateFormat.format(Date())
    }

    fun operator() {
        val operatorView: TextView = findViewById(R.id.operator)
        val operatorName = telephonyManager?.networkOperatorName
        operatorView.text = operatorName.toString()
    }

    fun model() {
        var manu = Build.MANUFACTURER
        var model = Build.MODEL
        var build = "$manu $model"

        val buildModel: TextView = findViewById(R.id.deviceModel)
        buildModel.text = build
    }

    fun viewLoc(latitude : String, longitude : String){
        val lat = latitude
        val long = longitude
        val gmmIntentUri = Uri.parse("geo:$lat,$long")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)


    }


    fun getLastLocation() {
        val long: TextView = findViewById(R.id.longitude)
        val lat: TextView = findViewById(R.id.latitude)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission()
        } else {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    mLocation = location
                    if (location != null) {
                        lat.text = location.latitude.toString()
                        long.text = location.longitude.toString()
                    }
                }
        }
    }

    private fun savedata(){
        val timeStamp: TextView = findViewById(R.id.timeStamp)
        val operator: TextView = findViewById(R.id.operator)
        val buildModel: TextView = findViewById(R.id.deviceModel)
        val long: TextView = findViewById(R.id.longitude)
        val lat: TextView = findViewById(R.id.latitude)
        val uploadView: TextView = findViewById(R.id.upload)
        val downloadView: TextView = findViewById(R.id.download)

        database = FirebaseDatabase.getInstance().getReference("data")


        val dataid = database.push().key
        val TimeStamp = timeStamp.text.toString()
        val Operator = operator.text.toString()
        val Model = buildModel.text.toString()
        val Longitude = long.text.toString()
        val Latitude = lat.text.toString()
        val UploadSpeed = uploadView.text.toString()
        val DownloadSpeed = downloadView.text.toString()



        val data = Data(dataid.toString(),TimeStamp,Operator,Model,Longitude,Latitude,UploadSpeed,DownloadSpeed)
        database.child(dataid.toString()).setValue(data).addOnSuccessListener {
            Toast.makeText(this, "Success",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed",Toast.LENGTH_SHORT).show()
        }
    }



    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            RequestPermissionCode
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            RequestPermissionCode
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
            RequestPermissionCode
        )
    }



}