package com.example.foodie_land.fragment


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodie_land.R
import com.example.foodie_land.adapter.PopularAdapter
import com.example.foodie_land.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewFlipper: ViewFlipper
    private val handler = Handler()
    private val flipInterval: Long = 3000 // 3 seconds

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using the binding object
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupViewFlipper()
        setupRecyclerView()
        binding.viewAllMenu.setOnClickListener {
            val bottomSheetDialog = MenuBootomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager,"Test")
        }
        return binding.root
    }

    private fun setupViewFlipper() {
        // Initialize the ViewFlipper
        viewFlipper = binding.imageSlider

        // Start the automatic flipping with slide_in_left animation
        startFlippingWithAnimation()
    }

    private fun startFlippingWithAnimation() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                // Show the next view with a slide-in-left animation
                val slideInLeftAnimation =
                    AnimationUtils.loadAnimation(requireActivity(), R.anim.slide_in_left)
                viewFlipper.inAnimation = slideInLeftAnimation

                viewFlipper.showNext()

                // Post the next flip
                handler.postDelayed(this, flipInterval)
            }
        }, flipInterval)
    }

    private fun setupRecyclerView() {
        // Initialize and set up the RecyclerView with the adapter
        val foodName = listOf("Burger", "Sandwich", "French Fries", "Potato Chips")
        val price = listOf("$10", "$8", "$5", "$4")
        val popularFoodImages = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4
        )
        val adapter = PopularAdapter(foodName, price, popularFoodImages, requireContext())
        binding.PopularRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.PopularRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Remove any pending callbacks to avoid memory leaks
        handler.removeCallbacksAndMessages(null)
    }
}




//import android.os.Bundle
//import android.os.Handler
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.animation.AnimationUtils
//import android.widget.ViewFlipper
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.foodie_land.R
//import com.example.foodie_land.adapter.PopularAdapter
//import com.example.foodie_land.databinding.FragmentHomeBinding
//
//
//class HomeFragment : Fragment() {
//    private lateinit var binding: FragmentHomeBinding
//    private lateinit var viewFlipper: ViewFlipper
//    private val handler = Handler()
//    private val flipInterval: Long = 3000 // 3 seconds
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
//
//        // Initialize the ViewFlipper
//        viewFlipper = view.findViewById(R.id.image_slider)
//
//        // Start the automatic flipping with slide_in_left animation
//        startFlippingWithAnimation()
//
//        binding= FragmentHomeBinding.inflate(inflater, container, false)
//
//        return view
//    }
//
//    private fun startFlippingWithAnimation() {
//        handler.postDelayed(object : Runnable {
//            override fun run() {
//                // Show the next view with a slide-in-left animation
//                val slideInLeftAnimation = AnimationUtils.loadAnimation(requireActivity(), R.anim.slide_in_left)
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
//    override fun onDestroyView() {
//        super.onDestroyView()
//        // Remove any pending callbacks to avoid memory leaks
//        handler.removeCallbacksAndMessages(null)
//    }
//    val foodName = listOf("Burger","momo","sandwich","item")
//    val Price = listOf("$5", "$7", "$8", "$10")
//    val popularFoodImages = listOf(R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4)
//    val adapter = PopularAdapter(foodName, Price, popularFoodImages)
//    binding.PopularRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//    binding.PopularRecyclerView.adapter = adapter
//}