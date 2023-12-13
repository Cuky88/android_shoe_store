package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoedetailBinding
import com.udacity.shoestore.models.Shoe


class ShoedetailFragment : Fragment() {
    private lateinit var viewModel: ShoedetailViewModel
    private lateinit var viewModelFactory: ShoedetailViewModelFactory
    private lateinit var binding: FragmentShoedetailBinding
    private lateinit var argBundle: ShoedetailFragmentArgs
    private val shoeListViewModel: ShoelistViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoedetail, container,false)
        argBundle = ShoedetailFragmentArgs.fromBundle(requireArguments())

        viewModelFactory = ShoedetailViewModelFactory(argBundle.shoe)
        viewModel = ViewModelProvider(this, viewModelFactory)[ShoedetailViewModel::class.java]
//        ViewModelProviders.of(activity).get(SongListViewModel::class.java)
        binding.shoedetailViewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.saveButton.setOnClickListener{
            shoeListViewModel.addShoe(Shoe(
                binding.shoenameEdit.text.toString(), binding.shoesizeEdit.text.toString().toDouble(),
                binding.companynameEdit.text.toString(), binding.descriptionEdit.text.toString(),
                mutableListOf("")
            ))

            findNavController().navigate(ShoedetailFragmentDirections.actionShoedetailFragmentToShoelistFragment())
        }

        binding.cancelButton.setOnClickListener{
            findNavController().navigate(ShoedetailFragmentDirections.actionShoedetailFragmentToShoelistFragment())
        }


        return binding.root
    }


}