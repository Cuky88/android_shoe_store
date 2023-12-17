package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoedetailBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoedetailFragment : Fragment() {
    private lateinit var viewModel: ShoedetailViewModel
    private lateinit var binding: FragmentShoedetailBinding

    private val shoeListViewModel: ShoelistViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoedetail, container,false)

        viewModel = ViewModelProvider(this)[ShoedetailViewModel::class.java]
        binding.shoedetailViewModel = viewModel
        binding.lifecycleOwner = this

        binding.saveButton.setOnClickListener{
            val shoe = viewModel.retrieveNewShoe()

            Timber.i(arrayListOf<String>(shoe.name, shoe.size.toString(), shoe.company, shoe.description).joinToString(" "))
            if(shoe.name.isEmpty() or shoe.company.isEmpty() or shoe.description.isEmpty() or (shoe.size == 0.0)){
                Toast.makeText(context, "Missing shoe details - not adding to list!", Toast.LENGTH_LONG).show()
            }else{
                shoeListViewModel.addShoe(shoe)
            }

            findNavController().navigate(ShoedetailFragmentDirections.actionShoedetailFragmentToShoelistFragment())
        }

        binding.cancelButton.setOnClickListener{
            findNavController().navigate(ShoedetailFragmentDirections.actionShoedetailFragmentToShoelistFragment())
        }


        return binding.root
    }


}