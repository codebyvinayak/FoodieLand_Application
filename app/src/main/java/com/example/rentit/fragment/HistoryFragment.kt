package com.example.rentit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentit.R
import com.example.rentit.adapter.RentAgainAdapter
import com.example.rentit.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var rentAgainAdapter: RentAgainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater,container,false )
        setupRecyclerView()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupRecyclerView(){
        val rentAgainVehicleName = listOf("Swift Saver ","Mountain Trailblazer","Summit Seeker","Mountain Maverick")
        val rentAgainVehiclePrice = listOf("600","1800","2000","3000")
        val rentAgainVehicleImage = arrayListOf(R.drawable.menu1,R.drawable.menu2,R.drawable.menu3,R.drawable.menu2)
        rentAgainAdapter = RentAgainAdapter(rentAgainVehicleName,rentAgainVehiclePrice,rentAgainVehicleImage)
        binding.RentAgainRecyclerView.adapter = rentAgainAdapter
        binding.RentAgainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
    companion object {

    }
}