package com.example.testcell

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView
import layout.ViewPagerAdapterFun
import layout.ViewPagerAdapterIklan
import me.relex.circleindicator.CircleIndicator3

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [newMainMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class newMainMenu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: Int? = 0


    private val titleIklan = mutableListOf<String>()
    private val imageIklan = mutableListOf<Int>()


    private val sambutanFun = mutableListOf<String>()
    private val pilihan1 = mutableListOf<Int>()
    private val pilihan2 = mutableListOf<Int>()

    private lateinit var mAuth: FirebaseAuth

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
        val View = inflater.inflate(R.layout.fragment_new_main_menu, container, false)
        val gambarUser : ImageView = View.findViewById(R.id.gambarUser2)
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser


        if (param3 == 0) {
            postToListFun()
            postToListIklan()
        }
        Glide.with(this).load(currentUser?.photoUrl).into(gambarUser)


        val viewPagerIklan : ViewPager2 = View.findViewById(R.id.viewPagerIklan)

        viewPagerIklan.adapter = ViewPagerAdapterIklan(imageIklan)
        viewPagerIklan.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        val viewPagerFun : ViewPager2 = View.findViewById(R.id.viewPagerFun)

        viewPagerFun.adapter = ViewPagerAdapterFun(sambutanFun,pilihan1,pilihan2)
        viewPagerFun.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator : CircleIndicator3 = View.findViewById(R.id.indicatorFun)
        indicator.setViewPager(viewPagerFun)

        val profilPic : CircleImageView = View.findViewById(R.id.gambarUser2)
        profilPic.setOnClickListener{
            Navigation.findNavController(View).navigate(R.id.action_newMainMenu_to_newProfile)
        }

        param3 = param3?.plus(1)
        // Inflate the layout for this fragment
        return View
    }
    private fun addToListIklan(images : Int){
        imageIklan.add(images)
    }

    private fun addToListFun(sambutan: String, pil1: Int, pil2 : Int){
        sambutanFun.add(sambutan)
        pilihan1.add(pil1)
        pilihan2.add(pil2)
    }

    private fun postToListIklan(){
        addToListIklan( R.drawable.iklandummy1)
        addToListIklan( R.drawable.coming_soon)
    }

    private fun postToListFun(){
        addToListFun("Test Cell",R.mipmap.signal_round, R.mipmap.opini_round)
        addToListFun("Quistioner",R.drawable.coming_soon, R.drawable.coming_soon)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment newMainMenu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            newMainMenu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    param3?.let { putInt(ARG_PARAM3, it) }
                }
            }
    }
}