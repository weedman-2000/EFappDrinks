package com.example.efapp.ui.fragments

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.efapp.R
import com.example.efapp.databinding.FragmentListApiBinding
import com.example.efapp.model.getData
import com.example.efapp.ui.viewmodels.ApiListViewModel

class ListApiFragment : Fragment() {

    private lateinit var binding: FragmentListApiBinding
    private lateinit var viewModel: ApiListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ApiListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RVListApiAdapter(listOf()){ drink ->
            val destination = ListApiFragmentDirections.actionListApiFragmentToDrinkApiDetailFragment(drink)
            findNavController().navigate(destination)
        }
        binding.rvListApi.adapter = adapter

        viewModel.drinks.observe(requireActivity()){
            adapter.drink = it
            adapter.notifyDataSetChanged()
        }

        viewModel.getDrinksFromService()
    }
}