package com.example.testcell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import layout.ViewPagerAdapterIklan

class voiceOfCustomer : AppCompatActivity() {

    private val titleList = mutableListOf<String>()
    private val imageList = mutableListOf<Int>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_of_customer)
        supportActionBar?.hide()

        postToList()
        val viewPagerFun : ViewPager2 = findViewById(R.id.viewPagerIklan)

        viewPagerFun.adapter = ViewPagerAdapterIklan(titleList,imageList)
        viewPagerFun.orientation = ViewPager2.ORIENTATION_HORIZONTAL


    }

    private fun addToList(title: String, images : Int){
        titleList.add(title)
        imageList.add(images)
    }

    private fun postToList(){
        addToList("Test Cell", R.drawable.wifi)
        addToList("Comming Soon", R.drawable.coming_soon)
        addToList("Profile", R.drawable.opinion)


    }






}