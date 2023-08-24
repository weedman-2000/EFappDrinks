package com.example.efapp.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.efapp.databinding.ItemDrinkBinding
import com.example.efapp.data.model.Drink

class RVListApiAdapter(var drink: List<Drink>, val onDrinkClick:(Drink) -> Unit): RecyclerView.Adapter<DrinkVH>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkVH {

        val binding = ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrinkVH(binding, onDrinkClick)

    }

    override fun getItemCount(): Int = drink.size



    override fun onBindViewHolder(holder: DrinkVH, position: Int) {
        holder.bind(drink[position])
    }


}

class DrinkVH(private val binding: ItemDrinkBinding, val onDrinkClick:(Drink) -> Unit): ViewHolder(binding.root){

    fun bind(drink : Drink){
        binding.txtDrinkTitle.text = drink.strDrink
        binding.txtCategoryDrink.text = drink.strCategory
        binding.txtGlassDrink.text = drink.strGlass
        binding.txtAlcoholicDrink.text = drink.strAlcoholic

        binding.root.setOnClickListener {
            onDrinkClick(drink)
        }
    }
}