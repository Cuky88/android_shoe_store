package com.udacity.shoestore.shoelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.Shoe

//class ShoedetailViewModelFactory(private val shoeName: String, private val companyName: String,
//                                 private val description: String, private val shoeSize: Double): ViewModelProvider.Factory {
 class ShoedetailViewModelFactory(private val shoe: Shoe): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoedetailViewModel::class.java)) {
            return ShoedetailViewModel(shoe.name, shoe.company, shoe.description, shoe.size) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}