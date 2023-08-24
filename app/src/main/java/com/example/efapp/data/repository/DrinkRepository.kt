package com.example.efapp.data.repository

import com.example.efapp.data.db.DrinkDao
import com.example.efapp.data.db.DrinkDataBase
import com.example.efapp.data.retrofit.RetrofitHelper
import com.example.efapp.model.Drink

class DrinkRepository(db: DrinkDataBase? = null) {

    private val dao: DrinkDao? = db?.drinkDao()

    suspend fun getDrinks(): List<Drink>{
        val response = RetrofitHelper.drinkService.getAllDrinks()
        return response.drinks
    }

    suspend fun getFavorites(): List<Drink>{
        dao?.let {
            return dao.getFavorites()
        }?: kotlin.run{
            return  listOf()
        }
    }

    suspend fun addDrinkToFavorites(drink: Drink){
        dao?.let {
            dao.insert(drink)
        }
    }
}