package com.example.rentit

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rentit.databinding.ActivityDetailsBinding
import com.example.rentit.model.CartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var vehicleName: String? = null
    private var vehicleImage: String? = null
    private var vehicleDescription: String? = null
    private var vehicleTypes: String? = null
    private var vehiclePrice: String? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialization FirebaseAuth
        auth = FirebaseAuth.getInstance()

        vehicleName = intent.getStringExtra("MenuItemName")
        vehicleDescription = intent.getStringExtra("MenuItemDescription")
        vehicleTypes = intent.getStringExtra("MenuItemTypes")
        vehiclePrice = intent.getStringExtra("MenuItemPrice")
        vehicleImage = intent.getStringExtra("MenuItemImage")

        with(binding) {
            detailVehicleName.text = vehicleName
            detailDescription.text = vehicleDescription
            detailTypes.text = vehicleTypes
            Glide.with(this@DetailsActivity).load(Uri.parse(vehicleImage)).into(detailVehicleImage)
        }

        binding.imageButton.setOnClickListener {
            finish()
        }
        binding.addItemButton.setOnClickListener {
            addItemToUpload()
        }
    }

    private fun addItemToUpload() {
        val database = FirebaseDatabase.getInstance().reference
        val userId = auth.currentUser?.uid ?: " "

        // Create a cartItems Object
        val cartItem = CartItems(
            vehicleName.toString(),
            vehiclePrice.toString(),
            vehicleDescription.toString(),
            vehicleImage.toString(),
            1
        )

        // Save Data to Cart Item o Firebase Database
        database.child("user").child(userId).child("CartItems").push().setValue(cartItem).addOnSuccessListener {
            Toast.makeText(this,"Items added to Cart SuccessFully 😃 ", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Items not Added 😔 ", Toast.LENGTH_SHORT).show()
        }
    }
}