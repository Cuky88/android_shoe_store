package com.udacity.shoestore.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoedetailViewModel():ViewModel() {
    var _shoeName= MutableLiveData<String>()
    val shoeName: LiveData<String> get() = _shoeName

    var _companyName= MutableLiveData<String>()
    val companyName: LiveData<String> get() = _companyName

    var _description= MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    var _shoeSize= MutableLiveData<String>()
    val shoeSize: LiveData<String> get() = _shoeSize

    init {
        Timber.tag("ShoedetailViewModel").i("ShoedetailViewModel created!")
    }

    fun retrieveNewShoe(): Shoe {
        if ((_shoeName.value == null) or (_companyName.value == null) or (_description.value == null) or (_shoeSize.value == null)){
             Timber.tag("ShoedetailViewModel").i("No shoe details entered!")
            return Shoe("", 0.0, "", "", mutableListOf(""))
        }
        return Shoe(_shoeName.value.toString(), _shoeSize.value.toString().toDouble(), _companyName.value.toString(), _description.value.toString(), mutableListOf(""))
    }
}