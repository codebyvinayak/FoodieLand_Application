package com.example.rentit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rentit.databinding.RentAgainItemBinding

class RentAgainAdapter(private val rentAgainVehicleName: List<String>,
                       private val rentAgainVehiclePrice: List<String>,
                       private val rentAgainVehicleImage:ArrayList<Int>) : RecyclerView
.Adapter<RentAgainAdapter.RentAgainViewHolder>() {


    override fun onBindViewHolder(holder: RentAgainAdapter.RentAgainViewHolder, position: Int) {
      holder.bind(rentAgainVehicleName[position],rentAgainVehiclePrice[position],rentAgainVehicleImage[position])
    }

//    override fun onBindViewHolder(holder: RentAgainAdapter.RentAgainViewHolder, position: Int) {
//        if (position < rentAgainVehicleName.size && position < rentAgainVehiclePrice.size && position < rentAgainVehicleImage.size) {
//            holder.bind(rentAgainVehicleName[position], rentAgainVehiclePrice[position], rentAgainVehicleImage[position])
//        }
//        else {
//             Handle the case where position is out of bounds
//           Show an error message with information about the error
//    val errorMessage = "Error: Index out of bounds at position $position"
//    holder.bind(errorMessage, "Error", R.drawable.error_image)
//   }
//     }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RentAgainAdapter.RentAgainViewHolder {
          val binding = RentAgainItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
         return RentAgainViewHolder(binding)
    }


    override fun getItemCount(): Int = rentAgainVehicleName.size

    class RentAgainViewHolder(private val binding: RentAgainItemBinding):RecyclerView.ViewHolder
        (binding.root) {
        fun bind(vehicleName: String, vehiclePrice: String, vehicleImage: Int) {
            binding.rentAgainVehicleName.text=vehicleName
            binding.rentAgainVehiclePrice.text=vehiclePrice
            binding.rentAgainVehicleImage.setImageResource(vehicleImage)
        }

    }



}