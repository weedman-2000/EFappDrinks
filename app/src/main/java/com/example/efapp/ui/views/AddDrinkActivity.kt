package com.example.efapp.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.efapp.databinding.ActivityAddDrinkBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddDrinkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDrinkBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDrinkBinding.inflate(layoutInflater)
        firestore = Firebase.firestore
        setContentView(binding.root)

        binding.btnRegisterDrink.setOnClickListener {
            val title = binding.tilTitleDrink.editText?.text.toString()
            val category = binding.tilCategoryDrink.editText?.text.toString()
            val alcoholic = binding.tilAlcoholicDrink.editText?.text.toString()
            val glass = binding.tilGlassDrink.editText?.text.toString()
            val iba = binding.tilIbaDrink.editText?.text.toString()
            val ingre1 = binding.tilIngredient1Drink.editText?.text.toString()
            val ingre2 = binding.tilIngredient2Drink.editText?.text.toString()
            val ingre3 = binding.tilIngredient3Drink.editText?.text.toString()
            val measure1 = binding.tilMeasure1Drink.editText?.text.toString()
            val measure2 = binding.tilMeasure2Drink.editText?.text.toString()
            val measure3 = binding.tilMeasure3Drink.editText?.text.toString()
            val favorite = binding.switchFavorite.isChecked
            val instruction = binding.tilInstructionsDrink.editText?.text.toString()

            if(title.isNotEmpty() &&
                category.isNotEmpty() &&
                alcoholic.isNotEmpty() &&
                glass.isNotEmpty() &&
                iba.isNotEmpty() &&
                ingre1.isNotEmpty() &&
                ingre2.isNotEmpty() &&
                ingre3.isNotEmpty() &&
                measure1.isNotEmpty() &&
                measure2.isNotEmpty() &&
                measure3.isNotEmpty() &&
                instruction.isNotEmpty()){
                addToFirestore(title,category,alcoholic,glass,iba,ingre1,ingre2,ingre3,measure1,measure2,measure3,favorite,instruction)
            }
        }
    }

    private fun addToFirestore(
        title: String,
        category: String,
        alcoholic:String,
        glass: String,
        iba: String,
        ingre1: String,
        ingre2: String,
        ingre3: String,
        measure1: String,
        measure2: String,
        measure3: String,
        favorite: Boolean,
        instruction: String
    ) {

        val newDrink = hashMapOf<String, Any>(
            "strDrink" to title  ,
            "strCategory" to category  ,
            "strAlcoholic" to alcoholic  ,
            "strGlass" to glass ,
            "strIBA" to iba ,
            "strIngredient1" to ingre1 ,
            "strIngredient2" to  ingre2 ,
            "strIngredient3" to ingre3 ,
            "strMeasure1" to measure1 ,
            "strMeasure2" to measure2 ,
            "strMeasure3" to measure3 ,
            "favorite" to favorite ,
            "strInstructions" to instruction  ,
        )

        firestore.collection("drink").add(newDrink)
            .addOnSuccessListener {
                Toast.makeText(this,"Drink agregada",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this,"No agregado",Toast.LENGTH_SHORT).show()
            }

    }
}