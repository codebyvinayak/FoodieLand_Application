package com.example.rentit.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rentit.DetailsActivity
import com.example.rentit.databinding.MenuItemBinding
import com.example.rentit.model.MenuData

class MenuAdapter(
    private val menuItems: List<MenuData>,
    private val requireContext: Context
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItems.size

    inner class MenuViewHolder(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    openDetailsActivity(position)
                }
            }
        }

        fun bind(position: Int) {
            val menuItem: MenuData = menuItems[position]
            binding.apply {
                menuVehicleName.text = menuItem.vehicleName
                menuPrice.text = menuItem.vehiclePrice
                val uri = Uri.parse(menuItem.vehicleImage)
                Glide.with(requireContext).load(uri).into(menuImage)
            }
        }
    }

    private fun openDetailsActivity(position: Int) {
        val menuItem = menuItems[position]

        //  A intent to open details Activity and Pass Data
        val intent = Intent(requireContext, DetailsActivity::class.java).apply {
            putExtra("MenuItemName", menuItem.vehicleName)
            putExtra("MenuItemImage", menuItem.vehicleImage)
            putExtra("MenuItemDescription", menuItem.vehicleDescription)
            putExtra("MenuItemTypes", menuItem.vehicleType)
            putExtra("MenuItemPrice", menuItem.vehiclePrice)
        }

        requireContext.startActivity(intent)
    }
}
