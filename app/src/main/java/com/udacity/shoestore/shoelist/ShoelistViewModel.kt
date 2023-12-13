package com.udacity.shoestore.shoelist

import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber
import kotlin.random.Random

class ShoelistViewModel: ViewModel() {
    private var _shoeList: MutableList<Shoe> = mutableListOf()

    private var _shoeListPopu = MutableLiveData<MutableList<Shoe>>()
    val shoeListPopu: LiveData<MutableList<Shoe>> get() = _shoeListPopu

    init {
        Timber.tag("ShoelistViewModel").i("ShoelistViewModel created!")
        resetShoeList()
        updateShoeListPopu()
    }

    private fun resetShoeList() {
        for (i in 1..23) {
            _shoeList.add(Shoe("Shoe #$i", Random(1).nextDouble(33.0, 47.0), "Audi AG", "Hier kommt eine klurze Beschreibung", mutableListOf()))
        }
    }

    private fun updateShoeListPopu(){
        _shoeListPopu.value = _shoeList
        Timber.tag("ShoelistViewModel").i("updateShoeListPopu - Shoe List size ${_shoeListPopu.value!!.size}")
    }

    fun addShoe(shoe: Shoe){
        _shoeList.add(shoe)
    }
}