package com.udacity.shoestore.shoelist

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.shape.MaterialShapeDrawable
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoelistBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoelistFragment : Fragment() {
    private lateinit var binding: FragmentShoelistBinding
    private lateinit var viewModel: ShoelistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Callback Code is form stackoverflow and android documentation
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button event
                // do nothing, just ignore backpress in this fragment
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoelist, container, false)

        viewModel = ViewModelProvider(requireActivity())[ShoelistViewModel::class.java]
        binding.shoelistViewModel = viewModel
        binding.lifecycleOwner = this

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(ShoelistFragmentDirections.actionShoelistFragmentToShoedetailFragment())
        }

        viewModel.shoeListPopu.observe(viewLifecycleOwner){
            createShoelistViews(it)
//            Toast.makeText(this.context, "New Shoe added, total: ${it.size} shoes.", Toast.LENGTH_LONG).show()
        }

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun createShoelistViews(shoelist:MutableList<Shoe>){
        shoelist.forEach { s ->
            val shoeView = createShoeTextView(s)
            binding.linearLayoutShoelist.addView(shoeView)
        }

        binding.invalidateAll()
    }


    private fun createShoeTextView(e:Shoe):TextView{
        val listItem = TextView (context)

        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            1f
        )

        layoutParams.setMargins(24, 0, 16, 2)
        listItem.layoutParams = layoutParams

        listItem.offsetTopAndBottom(8)
        listItem.id = View.generateViewId()
        listItem.text = e.name
        listItem.textSize = 24F
        listItem.setPadding(0, 2, 0, 2)
        listItem.background = this.context?.let { ContextCompat.getDrawable(it, android.R.drawable.editbox_background) }
//        listItem.background = this.context?.let { ContextCompat.getDrawable(it, R.drawable.border) }

        return listItem
    }

}
