package com.example.testcell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import layout.ViewPagerAdapterFun
import layout.ViewPagerAdapterIklan

class voiceOfCustomer : AppCompatActivity() {

    private val titleIklan = mutableListOf<String>()
    private val imageIklan = mutableListOf<Int>()

    private val pilihan1 = mutableListOf<Int>()
    private val pilihan2 = mutableListOf<Int>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_of_customer)
        supportActionBar?.hide()

        postToListFun()
        postToListIklan()

        val viewPagerIklan : ViewPager2 = findViewById(R.id.viewPagerIklan)

        viewPagerIklan.adapter = ViewPagerAdapterIklan(titleIklan,imageIklan)
        viewPagerIklan.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        val viewPagerFun : ViewPager2 = findViewById(R.id.viewPagerFun)

        viewPagerFun.adapter = ViewPagerAdapterFun(pilihan1,pilihan2)
        viewPagerFun.orientation = ViewPager2.ORIENTATION_HORIZONTAL

    }

    private fun addToListIklan(title: String, images : Int){
        titleIklan.add(title)
        imageIklan.add(images)
    }

    private fun addToListFun(pil1: Int, pil2 : Int){
        pilihan1.add(pil1)
        pilihan2.add(pil2)
    }

    private fun postToListIklan(){
        addToListIklan("Test Cell", R.drawable.wifi)
        addToListIklan("Comming Soon", R.drawable.coming_soon)
        addToListIklan("Profile", R.drawable.opinion)
    }

    private fun postToListFun(){
        addToListFun(R.mipmap.signal, R.mipmap.signal_foreground)
        addToListFun(R.drawable.coming_soon, R.drawable.coming_soon)
        addToListFun(R.drawable.opinion, R.drawable.opinion)
    }
}