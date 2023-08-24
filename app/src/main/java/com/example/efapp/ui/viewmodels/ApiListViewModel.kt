package com.example.efapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.efapp.data.repository.DrinkRepository
import com.example.efapp.model.Drink
import com.example.efapp.model.getData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiListViewModel: ViewModel() {
    val drinks : MutableLiveData<List<Drink>> = MutableLiveData<List<Drink>>()
    val repository = DrinkRepository()

    fun getAllDrinks(){
        val drinkList = getData()
        drinks.value = drinkList
    }

    fun getDrinksFromService(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getDrinks()
            drinks.postValue(response)
        }
    }
}