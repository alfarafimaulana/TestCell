package com.example.testcell

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import layout.ViewPagerAdapterIklan
import layout.ViewPagerAdapterIklanVoc


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [voiceOfC.newInstance] factory method to
 * create an instance of this fragment.
 */
class voiceOfC : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: Int? = 0

    private val titleIklan = mutableListOf<String>()
    private val imageIklan = mutableListOf<Int>()


    private val sambutanFun = mutableListOf<String>()
    private val pilihan1 = mutableListOf<Int>()
    private val pilihan2 = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getInt(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val View = inflater.inflate(R.layout.fragment_new_voice_of_c, container, false)
        val vocButton : ImageView = View.findViewById(R.id.vocButton)

        vocButton.setOnClickListener {
            val dashboardIntent = Intent(View.context, newQuistionerV2::class.java)
            View.context.startActivity(dashboardIntent)
        }
        vocButton.setOnLongClickListener(object: View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                val dialogBuilder = AlertDialog.Builder(activity!!)
                dialogBuilder.setMessage("Simple Quistoner for better lifestyle")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    .setPositiveButton("Got it", DialogInterface.OnClickListener {
                            dialog, id ->
                        dialog.dismiss()

                    })

                val alert = dialogBuilder.create()
                alert.setTitle("Quistoner")
                alert.show()

                return true  }
        })



        if (param3 == 0) {
            postToListIklan()
        }
        val viewPagerIklan : ViewPager2 = View.findViewById(R.id.viewIklan2)

        viewPagerIklan.adapter = ViewPagerAdapterIklanVoc(imageIklan)
        viewPagerIklan.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator : WormDotsIndicator = View.findViewById(R.id.indicatorIklan2)
        indicator.setViewPager2(viewPagerIklan)



        return View
    }
    private fun addToListIklan(images : Int){
        imageIklan.add(images)
    }

    private fun postToListIklan(){
        addToListIklan( R.drawable.iklan_voc)
        addToListIklan( R.drawable.coming_soon)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment voiceOfC.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            voiceOfC().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    param3?.let { putInt(ARG_PARAM3, it) }
                }
            }
    }
}