package com.example.efapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.efapp.model.Drink

@Dao
interface DrinkDao
{
    @Insert
    suspend fun insert(user: Drink)

    // Actualizar un usuario
    @Update
    suspend fun update(user: Drink)

    // Eliminar un usuario
    @Delete
    suspend fun delete(user: Drink)

    // Obtener todos los usuarios
    @Query("SELECT * FROM drink")
    suspend fun getFavorites(): List<Drink>


}