package com.example.efapp.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.efapp.data.db.DrinkDataBase
import com.example.efapp.data.repository.DrinkRepository
import com.example.efapp.data.model.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrinkDetailViewModel(application: Application): AndroidViewModel(application) {
    private val repository : DrinkRepository
    init{
        val db = DrinkDataBase.getDatabase(application)
        repository = DrinkRepository(db)
    }

    fun addFavorites(drink: Drink){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDrinkToFavorites(drink)
        }
    }
}