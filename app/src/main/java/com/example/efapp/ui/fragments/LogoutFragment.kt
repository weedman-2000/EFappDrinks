package com.example.efapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.efapp.R
import com.example.efapp.databinding.ActivityAddDrinkBinding
import com.example.efapp.databinding.FragmentLogoutBinding
import com.example.efapp.ui.views.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class LogoutFragment : Fragment() {

    private lateinit var binding: FragmentLogoutBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent  = Intent(activity,LoginActivity::class.java)

        binding.btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(intent)

        }
    }
}