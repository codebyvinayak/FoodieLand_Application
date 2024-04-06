package com.example.rentit.model

data class CartItems(
    var vehicleName: String? = null,
    var vehiclePrice: String? = null,
    var vehicleDescription: String? = null,
    var vehicleImage: String? = null,
    var vehicleQuantity: Int? = null,
    var vehicleType: String? = null
)