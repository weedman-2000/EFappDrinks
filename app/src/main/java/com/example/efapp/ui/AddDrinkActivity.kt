package com.example.efapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}