package com.example.rentit.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentit.PayOutActivity
import com.example.rentit.adapter.CartAdapter
import com.example.rentit.databinding.FragmentCartBinding
import com.example.rentit.model.CartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var vehicleNames: MutableList<String>
    private lateinit var vehiclePrices: MutableList<String>
    private lateinit var vehicleDescriptions: MutableList<String>
    private lateinit var vehicleImagesUri: MutableList<String>
    private lateinit var vehicleTypes: MutableList<String>
    private lateinit var quantity: MutableList<Int>
    private lateinit var cartAdapter: CartAdapter
    private lateinit var userId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        retrieveCartItems()

        binding.proceedButton.setOnClickListener {
            // get order items details before proceeding to check out
            getOrderItemsDetail()
            val intent = Intent(requireContext(), PayOutActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun getOrderItemsDetail() {

        val orderReference:DatabaseReference =database.reference.child("user").child(userId).child("CartItems")

       val vehicleName = mutableListOf<String>()
       val vehiclePrice = mutableListOf<String>()
       val vehicleDescription = mutableListOf<String>()
       val vehicleImage  = mutableListOf<String>()
       val vehicleType = mutableListOf<String>()
       val vehicleQuantities = cartAdapter.getUpdatedItemsQuantities()

        orderIdReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (vehicleSnapshot: DataSnapshot in snapshot.children) {

                    // Get the cartItems to respective List
                    val orderItems: CartItems? = vehicleSnapshot.getValue(CartItems::class.java)

                    // Add items details into lists
                    orderItems?.vehicleName?.let { vehicleName.add(it) }
                    orderItems?.vehiclePrice?.let { vehiclePrice.add(it) }
                    orderItems?.vehicleDescription?.let { vehicleDescription.add(it) }
                    orderItems?.vehicleImage?.let { vehicleImage.add(it) }
                    orderItems?.vehicleType?.let { vehicleType.add(it) }

                    orderNow(vehicleName, vehiclePrice, vehicleDescription, vehicleImage, vehicleType, vehicleQuantities)
                }
            }

            override fun onCancelled(error: DatabaseError) {
               Toast.makeText()
            }
        })

    }

    private fun orderNow(vehicleName: MutableList<String>, vehiclePrice: MutableList<String>, vehicleDescription: MutableList<String>, vehicleImage: MutableList<String>, vehicleType: MutableList<String>, vehicleQuantities: MutableList<Int>) {

    }

    private fun retrieveCartItems() {

        // Database Reference to the Firebase
        database = FirebaseDatabase.getInstance()
        userId = auth.currentUser?.uid?:" "
        val vehicleReference : DatabaseReference = database.reference.child("user").child(userId).child("CartItems")

        //List to store Cart Items
        vehicleNames = mutableListOf()
        vehiclePrices = mutableListOf()
        vehicleDescriptions = mutableListOf()
        vehicleImagesUri = mutableListOf()
        vehicleTypes = mutableListOf()
        quantity = mutableListOf()

        // Fetch Data from the Database
        vehicleReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (vehicleSnapshot in snapshot.children){

                    // Get the CartItems Object from the child Node
                    val cartItems = vehicleSnapshot.getValue(CartItems::class.java)

                    // Add Cart Items Details to the list
                    cartItems?.vehicleName?.let { vehicleNames.add(it) }
                    cartItems?.vehiclePrice?.let { vehiclePrices.add(it) }
                    cartItems?.vehicleDescription?.let { vehicleDescriptions.add(it) }
                    cartItems?.vehicleImage?.let { vehicleImagesUri.add(it) }
                    cartItems?.vehicleQuantity?.let { quantity.add(it) }
                    cartItems?.vehicleType?.let { vehicleTypes.add(it) }
                }

                setAdapter()
            }

            private fun setAdapter() {

                val adapter = CartAdapter(requireContext(),vehicleNames,vehiclePrices,vehicleDescriptions,vehicleImagesUri,quantity,vehicleTypes)
                binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.cartRecyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Data Not Fetch", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        // You can add companion object members if needed
    }
}
