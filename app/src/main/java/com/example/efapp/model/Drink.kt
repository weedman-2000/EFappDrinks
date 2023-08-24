package com.example.efapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "drink")
@Parcelize
data class Drink(
    @PrimaryKey
    val idDrink: String,
    val strDrink: String,
    val strCategory: String,
    val strIBA: String?,
    val strAlcoholic: String,
    val strGlass: String,
    val strInstructions: String,
    val strDrinkThumb: String,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    var favorite: Boolean = false
): Parcelable


fun getData() : List<Drink>{
    return listOf(
        Drink("11288",
                "Cuba Libre",
                "Ordinary Drink",
                "Contemporary Classics" ,
                "Alcoholic",
                "Highball glass",
                "Build all ingredients in a Collins glass filled with ice. Garnish with lime wedge.",
                "https://www.thecocktaildb.com/images/media/drink/wmkbfj1606853905.jpg",
                "Light rum",
                "Lime",
                "Coca-Cola",
                "",
                "",
                "2 oz ",
                "Juice of 1/2 ",
                "",
                "",
                ""),
        Drink("11288",
            "Cuba Libre2",
            "Ordinary Drink2",
            "Contemporary Classics2" ,
            "Alcoholic2",
            "Highball glass",
            "Build all ingredients in a Collins glass filled with ice. Garnish with lime wedge.",
            "https://www.thecocktaildb.com/images/media/drink/wmkbfj1606853905.jpg",
            "Light rum",
            "Lime",
            "Coca-Cola",
            "",
            "",
            "2 oz ",
            "Juice of 1/2 ",
            "",
            "",
            ""),
        )
    }
