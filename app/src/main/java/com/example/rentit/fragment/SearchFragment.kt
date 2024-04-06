package com.example.rentit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentit.R
import com.example.rentit.adapter.MenuAdapter
import com.example.rentit.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter
    private val originalMenuVehicleName =
        listOf("Swift Saver ", "Mountain Trailblazer", "Summit Seeker", "Mountain Maverick")
    private val originalMenuItemPrice = listOf("600", "1800", "2000", "3000")
    private val originalMenuImage = listOf(
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu4,
        R.drawable.menu5,
        R.drawable.menu6,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val filteredMenuVehicleName = mutableListOf<String>()
    private val filteredMenuItemPrice = mutableListOf<String>()
    private val filteredMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
//        adapter = MenuAdapter(
//            filteredMenuVehicleName,
//            filteredMenuItemPrice,
//            filteredMenuImage,
//            requireContext()
//        )
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        setupSearchView()


        showAllMenu()
        return binding.root
    }

    private fun showAllMenu() {
        filteredMenuVehicleName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        filteredMenuVehicleName.addAll(originalMenuVehicleName)
        filteredMenuItemPrice.addAll(originalMenuItemPrice)
        filteredMenuImage.addAll(originalMenuImage)

        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(/* listener = */ object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }

    private fun filterMenuItems(query: String) {
        filteredMenuVehicleName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        originalMenuVehicleName.forEachIndexed { index, vehicleName ->
            if (vehicleName.contains(query, ignoreCase = true)) {
                filteredMenuVehicleName.add(vehicleName)
                filteredMenuItemPrice.add(originalMenuItemPrice[index])
                filteredMenuImage.add(originalMenuImage[index])
            }
        }
        adapter.notifyDataSetChanged()
    }

    companion object {

    }
}