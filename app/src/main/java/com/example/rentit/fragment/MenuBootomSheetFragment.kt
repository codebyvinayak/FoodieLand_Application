package com.example.rentit.fragment

import  android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentit.adapter.MenuAdapter
import com.example.rentit.databinding.FragmentMenuBootomSheetBinding
import com.example.rentit.model.MenuData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MenuBootomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMenuBootomSheetBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var menuDatas: MutableList<MenuData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBootomSheetBinding.inflate(inflater,container,false)

        binding.buttonBack.setOnClickListener{
            dismiss()
        }


        retrieveMenuItems()
        return binding.root
    }

    private fun retrieveMenuItems() {
        database = FirebaseDatabase.getInstance()
        val vehicleRef = database.reference.child("menu")
        menuDatas = mutableListOf()

        vehicleRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                        for (vehicleSnapshot in snapshot.children){
                            val menuData = vehicleSnapshot.getValue(MenuData::class.java)
                            menuData?.let { menuDatas.add(it) }
                        }
                Log.d("ITEMS","onDataChange: Data Received")

                // One data receive , set to Adapter
                setAdapter()
            }

            private fun setAdapter(){  
                if (menuDatas.isNotEmpty()){
                    val adapter = MenuAdapter(menuDatas, requireContext())
                    binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding.menuRecyclerView.adapter = adapter
                    Log.d("ITEMS", "setAdapter: data set")
                }else{
                    Log.d("ITEMS","setAdapter: data not set")
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    companion object {

    }

}

//package com.example.rentit.fragment
//
//
//import android.os.Bundle
//import android.os.Handler
//import android.view.LayoutInflater
//import android.view.Menu
//import android.view.MenuItem
//import android.view.View
//import android.view.ViewGroup
//import android.view.animation.AnimationUtils
//import android.widget.ViewFlipper
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.rentit.R
//import com.example.rentit.adapter.MenuAdapter
//import com.example.rentit.adapter.PopularAdapter
//import com.example.rentit.databinding.FragmentHomeBinding
//import com.example.rentit.model.MenuData
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class HomeFragment : Fragment() {
//    private lateinit var binding: FragmentHomeBinding
//    private lateinit var database: FirebaseDatabse
//    private lateinit var menuIten: MutableList<MenuItem>
//
//    //private lateinit var viewFlipper: ViewFlipper
//    // private val handler = Handler()
//    //private val flipInterval: Long = 3000 // 3 seconds
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout using the binding object
//        binding = FragmentHomeBinding.inflate(inflater, container, false)
//        setupViewFlipper()
//        setupRecyclerView()
//        binding.viewAllMenu.setOnClickListener {
//            val bottomSheetDialog = MenuBootomSheetFragment()
//            bottomSheetDialog.show(parentFragmentManager,"Test")
//        }
//        // Retrive and display popular mune items
//        retriveAndDisplaypopularItems()
//
//        return binding.root
//    }
//
//    private fun retriveAndDisplaypopularItems() {
//        ///get reference to the database
//        database = FirebaseDatabase.getInstance()
//        val vehicleRef:DatabaseReference = database.reference.child("menu")
//        menuDatas = mutableListOf()
//
//        /// retrive from menu items from the database
//        vehicleRef.addListenerForSingleValueEvent(object :ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (vehicleSnapshort :DataSnapshot in snapshot.children){
//                    val menuItem:MenuItem? = vehicleSnapshort.getValue((MenuItem::class.java))
//                    menuItem?.let {menuDatas.add(it)}
//                }
//                //display a random popular items
//                randompopularitems()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
//    }
//
//    private fun randompopularitems() {
//        //create as shuffled list of menu items
//        val index : List<int> = menuData.indices.toList().shuffled()
//        val numItemToShow = 6
//        val subsetMenuItems : List<MenuData> = index.take<MenuData>(numItemToShow).map { menuData ->  [it] }
//
//        setPupularItemAdpter(subsetMenuItems)
//    }
//
//    private fun setPupularItemAdpter(subsetMenuItems: List<MenuData>) {
//        val adapter = MenuAdapter( subsetMenuItems, requireContext())
//        binding.PopularRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.PopularRecyclerView.adapter = adapter
//    }
//
//    private fun setupViewFlipper() {
//        // Initialize the ViewFlipper
//        viewFlipper = binding.imageSlider
//
//        // Start the automatic flipping with slide_in_left animation
//        startFlippingWithAnimation()
//    }
//
//    private fun startFlippingWithAnimation() {
//        handler.postDelayed(object : Runnable {
//            override fun run() {
//                // Show the next view with a slide-in-left animation
//                val slideInLeftAnimation =
//                    AnimationUtils.loadAnimation(requireActivity(), R.anim.slide_in_left)
//                viewFlipper.inAnimation = slideInLeftAnimation
//
//                viewFlipper.showNext()
//
//                // Post the next flip
//                handler.postDelayed(this, flipInterval)
//            }
//        }, flipInterval)
//    }
//
//    private fun setupRecyclerView() {
//
//
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        // Remove any pending callbacks to avoid memory leaks
//        handler.removeCallbacksAndMessages(null)
//    }
//}
