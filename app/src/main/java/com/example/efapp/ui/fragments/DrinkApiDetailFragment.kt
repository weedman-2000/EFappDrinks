package com.example.efapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.efapp.R
import com.example.efapp.databinding.FragmentDrinkApiDetailBinding
import com.example.efapp.data.model.Drink
import com.example.efapp.ui.fragments.DrinkApiDetailFragmentArgs
import com.google.android.material.snackbar.Snackbar

class DrinkApiDetailFragment : Fragment() {

    private lateinit var binding: FragmentDrinkApiDetailBinding
    private val args: DrinkApiDetailFragmentArgs by navArgs()
    private lateinit var drink : Drink
    private lateinit var viewModel: DrinkDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drink = args.drink
        viewModel = ViewModelProvider(requireActivity())[DrinkDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrinkApiDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtDrinkTitle.text = drink.strDrink
        binding.txtCategoryDrink.text = drink.strCategory
        binding.txtGlassDrink.text = drink.strGlass
        binding.txtInstructionsDrink.text = drink.strInstructions
        Glide.with(this).load(drink.strDrinkThumb).into(binding.ivDrink)

        if(drink.favorite){
            binding.btnAddFav.visibility = View.GONE
        }

        binding.btnAddFav.setOnClickListener {
            drink.favorite = true
            viewModel.addFavorites(drink)
            Snackbar.make(binding.root,"Agregado a Favoritos",Snackbar.LENGTH_SHORT).show()
        }

    }

}