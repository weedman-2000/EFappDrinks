package com.example.efapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.efapp.model.Drink

@Database(entities = [Drink::class], version = 1)
abstract class DrinkDataBase: RoomDatabase() {
    abstract fun drinkDao(): DrinkDao

    companion object{
        private var instance: DrinkDataBase? = null
        fun getDatabase(context: Context): DrinkDataBase{
            val tempInstance = instance
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val _instance = Room.databaseBuilder(
                    context.applicationContext,
                    DrinkDataBase::class.java,
                    "drinkdb"
                ).build()
                instance = _instance
                return _instance
            }
        }
    }
}