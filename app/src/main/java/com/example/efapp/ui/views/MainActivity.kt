package com.example.efapp.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.efapp.databinding.ActivityMainBinding
import androidx.navigation.ui.setupWithNavController
import com.example.efapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_app) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvMenu.setupWithNavController(navController)

        binding.btnRegisterDrink.setOnClickListener {
            val intent = Intent(this, AddDrinkActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}