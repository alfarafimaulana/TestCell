package com.example.testcell

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.ConnectivityManager
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*


class TestAct : AppCompatActivity() {
    private var telephonyManager: TelephonyManager? = null
    private val myPhoneStateListener: MyPhoneStateListener = MyPhoneStateListener()
    private var speedTestTask: SpeedTestViewModel = SpeedTestViewModel()
    private var wifiManager: WifiManager? = null

    //private lateinit var fusedLocationClient: FusedLocationProviderClient
    val RequestPermissionCode = 1
    var mLocation: Location? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var database : DatabaseReference

    // Background variable
    var timeStampBg = ""
    var modelBg = ""
    var latBg = ""
    var longBg = ""
    var opBg = ""

    var signalBg = ""
    var latencyBg = ""
    var uploadSpeedBg = ""
    var downloadSpeedBg = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testone)
        supportActionBar?.hide()

        telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager!!.listen(myPhoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS)
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        myPhoneStateListener.setCallback(::updateSignal)
        speedTestTask.setDownloadCallback(::updateDownloadSpeed)
        speedTestTask.setUploadCallback(::updateUploadSpeed)
        speedTestTask.setLatencyCallback(::updateLatency)
        speedTestTask.setBusyCallback (::updateBusy)

        var connectivityManager = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetInfo = connectivityManager.activeNetworkInfo
        var mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)




        val timeStamp: EditText = findViewById(R.id.timeStamp)
        val operator: EditText = findViewById(R.id.operator)
        val buildModel: EditText = findViewById(R.id.deviceModel)
        val long: EditText = findViewById(R.id.longitude)
        val lat: EditText = findViewById(R.id.latitude)
        val uploadView: EditText = findViewById(R.id.upload)
        val downloadView: EditText = findViewById(R.id.download)



        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


        val loc: Button = findViewById(R.id.loc2)
        loc.setOnClickListener{
            val longValue = long.text.toString()
            val latValue = lat .text.toString()
            viewLoc(latValue,longValue)
        }

//        val refreshBtn: Button = findViewById(R.id.refresh2)
//        refreshBtn.setOnClickListener {
            //this button disable, in near feature the refresh button will be removed


//            val speedBtn: Button = findViewById(R.id.speed2)
//            if (wifiManager!!.isWifiEnabled) {
//                Toast.makeText(this, "Harap menggunakan jaringan seluler",Toast.LENGTH_LONG).show()
//                speedBtn.isEnabled = false
//            }
//            else{
//                Toast.makeText(this, "Fungsi Test sudah dapat digunakan",Toast.LENGTH_SHORT).show()
//                speedBtn.isEnabled = true
//            }
//        }
        val sumbitBtn: Button = findViewById(R.id.submitData2)
        sumbitBtn.setOnClickListener {
            val sumbitBtn: Button = findViewById(R.id.submitData2)
            if (timeStamp.text.trim().isEmpty() && uploadView.text.trim().isEmpty() && downloadView.text.trim().isEmpty()){
                Toast.makeText(this, "segera memulai test",Toast.LENGTH_SHORT).show()
            }
            else{
                savedata()
                sumbitBtn.isEnabled = false
            }
        }

        val speedBtn: Button = findViewById(R.id.speed2)
        speedBtn.setOnClickListener {
            activeNetInfo = connectivityManager.activeNetworkInfo
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

            if (wifiManager!!.isWifiEnabled) {
                Toast.makeText(this, "Wifi terdeteksi, matikan wifi dan tekan refresh",Toast.LENGTH_SHORT).show()
            }
            else{
                if ( activeNetInfo != null ) {
                    sumbitBtn.isEnabled = true
                    getLastLocation()
                    timeStamp.setText(timeStamp())
                    operator()
                    model()
                    speedTestTask.runTest()
                    speedTestTask.runPing()
                }
//                if (mobNetInfo != null) {
//                    Toast.makeText(this, "Mobile Network Type : " + mobNetInfo.typeName, Toast.LENGTH_SHORT).show()
//                }

                else{
                    Toast.makeText(this, "Tolong nyalakan data anda",Toast.LENGTH_SHORT).show()
                }
            }
        }





        if (wifiManager!!.isWifiEnabled) {
            Toast.makeText(this, "Wifi terdeteksi, matikan wifi dan tekan refresh",Toast.LENGTH_SHORT).show()
            speedBtn.isEnabled = false
        }
        else{
            speedBtn.isEnabled = true
        }



    }


    fun updateUploadSpeed(speed: BigDecimal) {
        this@TestAct.runOnUiThread {
            val uploadView: TextView = findViewById(R.id.upload)
            uploadView.text = speed.toInt().toString()
            uploadSpeedBg = speed.toInt().toString()

        }
    }
    fun updateDownloadSpeed(speed: BigDecimal) {
        this@TestAct.runOnUiThread {
            val downloadView: TextView = findViewById(R.id.download)
            downloadView.text = speed.toInt().toString()
            downloadSpeedBg = speed.toInt().toString()
        }
    }
    fun updateLatency(latency: Int) {
        this@TestAct.runOnUiThread {
            val latencyView: TextView = findViewById(R.id.latency)
            latencyView.text = latency.toString()
            latencyBg = latency.toString()
        }
    }

    fun updateBusy(busy: Boolean) {
        this@TestAct.runOnUiThread {
            val sumbitBtn: Button = findViewById(R.id.submitData2)
            sumbitBtn.isEnabled = !busy
        }
    }

    fun updateSignal(strengthVal: Int) {
        val strengthView: TextView = findViewById(R.id.signal)
        strengthView.text = strengthVal.toString()
        signalBg = strengthVal.toString()
    }



    fun timeStamp(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)
        timeStampBg = dateFormat.format(Date()).toString()
        return dateFormat.format(Date())
    }

    fun operator() {
        val operatorView: TextView = findViewById(R.id.operator)
        val operatorName = telephonyManager?.networkOperatorName
        operatorView.text = operatorName.toString()
        opBg = operatorName.toString()
    }

    fun model() {
        var manu = Build.MANUFACTURER
        var model = Build.MODEL
        var build = "$manu $model"

        val buildModel: TextView = findViewById(R.id.deviceModel)
        buildModel.text = build
        modelBg = build
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

                        latBg = location.latitude.toString()
                        longBg = location.longitude.toString()
                    }
                }
        }
    }

    fun savedata(){

        database = FirebaseDatabase.getInstance().getReference("data")
        var mode = 2
        if (mode == 1) {

        val timeStamp: TextView = findViewById(R.id.timeStamp)
        val operator: TextView = findViewById(R.id.operator)
        val buildModel: TextView = findViewById(R.id.deviceModel)
        val long: TextView = findViewById(R.id.longitude)
        val lat: TextView = findViewById(R.id.latitude)
        val signalStr: TextView = findViewById(R.id.signal)
        val late: TextView = findViewById(R.id.latency)
        val uploadView: TextView = findViewById(R.id.upload)
        val downloadView: TextView = findViewById(R.id.download)


        val dataid = database.push().key
        val TimeStamp = timeStamp.text.toString()
        val Operator = operator.text.toString()
        val Model = buildModel.text.toString()
        val Longitude = long.text.toString()
        val Latitude = lat.text.toString()
        val Signal = signalStr.text.toString()
        val Latency = late.text.toString()
        val UploadSpeed = uploadView.text.toString()
        val DownloadSpeed = downloadView.text.toString()

        val data = Data(dataid.toString(),TimeStamp,Operator,Model,Longitude,Latitude,Signal,Latency,UploadSpeed,DownloadSpeed)
        database.child(dataid.toString()).setValue(data).addOnSuccessListener {
            Toast.makeText(this, "Terima kasih atas masukannya",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed",Toast.LENGTH_SHORT).show()}
//        .addOnCompleteListener{
//            val signInIntent = Intent(this, MainActivity::class.java)
//            startActivity(signInIntent)
//            finish()
//        }



        }
        if(mode == 2){

            val dataid = database.push().key
            val TimeStamp = timeStampBg
            val Operator = opBg
            val Model = modelBg
            val Longitude = longBg
            val Latitude = latBg
            val Signal = signalBg
            val Latency = latencyBg
            val UploadSpeed = uploadSpeedBg
            val DownloadSpeed = downloadSpeedBg

            val data = Data(dataid.toString(),TimeStamp,Operator,Model,Longitude,Latitude,Signal,Latency,UploadSpeed,DownloadSpeed)
            database.child(dataid.toString()).setValue(data).addOnSuccessListener {
                Toast.makeText(this, "Terima kasih atas masukannya",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed",Toast.LENGTH_SHORT).show()}
//            .addOnCompleteListener{
//                val signInIntent = Intent(this, MainActivity::class.java)
//                startActivity(signInIntent)
//                finish()
//            }

        }
    }



    fun requestPermission() {
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