package com.example.rentit.model

data class MenuData(
    val vehicleName:String?=null,
    val vehiclePrice:String?=null,
    val vehicleDescription:String?=null,
    val vehicleType:String?=null,
    val vehicleImage:String?=null // vehicleimageurl ki jaga tune database mai jo name rakha hai vo likhna varna error aayegi
) // aur kuch jarurat ho to add kar na bhai...!


