package com.udacity.shoestore.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber


class ShoedetailViewModel(shoeNameVar: String, companyNameVar: String,
                                   descriptionVar: String, shoeSizeVar: Double):ViewModel() {

    private var _shoeName= MutableLiveData<String>()
    val shoeName: LiveData<String> get() = _shoeName

    private var _companyName= MutableLiveData<String>()
    val companyName: LiveData<String> get() = _companyName

    private var _description= MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    private var _shoeSize= MutableLiveData<Double>()
    val shoeSize: LiveData<Double> get() = _shoeSize

    init {
        Timber.tag("ShoedetailViewModel").i("ShoedetailViewModel created!")

        _shoeName.value = shoeNameVar.toString()
        _companyName.value = companyNameVar.toString()
        _description.value = descriptionVar.toString()
        _shoeSize.value = shoeSizeVar
    }

}