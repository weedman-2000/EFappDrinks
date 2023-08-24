package com.example.efapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.efapp.R
import com.example.efapp.databinding.FragmentDrinkApiDetailBinding
import com.example.efapp.databinding.FragmentFavDataBaseBinding


class FavDataBaseFragment : Fragment() {

    private lateinit var binding: FragmentFavDataBaseBinding
    private lateinit var viewModel: DrinkFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DrinkFavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavDataBaseBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RVListApiAdapter(listOf()){ drink ->
            val destination = FavDataBaseFragmentDirections.actionFavDataBaseFragmentToDrinkApiDetailFragment(drink)
            findNavController().navigate(destination)
        }
        binding.rvListDatabase.adapter = adapter

        viewModel.favorites.observe(requireActivity()){
            adapter.drink = it
            adapter.notifyDataSetChanged()
        }

        viewModel.getFavorites()

    }



}