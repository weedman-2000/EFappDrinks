package com.example.efapp.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.efapp.data.db.DrinkDataBase
import com.example.efapp.data.repository.DrinkRepository
import com.example.efapp.model.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrinkFavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val repository : DrinkRepository
    private  var _favorites: MutableLiveData<List<Drink>> = MutableLiveData()
    var favorites: LiveData<List<Drink>> = _favorites

    init{
        val db = DrinkDataBase.getDatabase(application)
        repository = DrinkRepository(db)
    }

    fun getFavorites(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getFavorites()
            _favorites.postValue(data)
        }
    }
}