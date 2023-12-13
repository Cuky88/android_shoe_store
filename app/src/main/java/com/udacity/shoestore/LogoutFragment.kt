package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLogoutBinding
import com.udacity.shoestore.databinding.FragmentShoelistBinding

class LogoutFragment : Fragment() {
    private lateinit var binding: FragmentLogoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(LogoutFragmentDirections.actionLogoutFragmentPop())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_logout, container, false)
        binding.logoutButton.setOnClickListener{
            findNavController().navigate(LogoutFragmentDirections.actionLogoutFragmentToLoginFragment())
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}