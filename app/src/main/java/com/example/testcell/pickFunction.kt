package com.example.testcell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import layout.ViewPagerAdapterPickFunction
import me.relex.circleindicator.CircleIndicator3

class pickFunction : AppCompatActivity() {

    private val titleList = mutableListOf<String>()
    private val imageList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_function)

        supportActionBar?.hide()

        postToList()
        val viewPagerFun : ViewPager2 = findViewById(R.id.viewPagerFun)

        viewPagerFun.adapter = ViewPagerAdapterPickFunction(titleList,imageList)
        viewPagerFun.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator : CircleIndicator3 = findViewById(R.id.indicator)
        indicator.setViewPager(viewPagerFun)


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


    override fun onBackPressed(){
        finish()
    }
}