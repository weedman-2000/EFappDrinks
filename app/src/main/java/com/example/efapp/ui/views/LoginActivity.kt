package com.example.efapp.ui.views

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.efapp.R
import com.example.efapp.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleLaucher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        googleLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){    result ->
            if(result.resultCode == RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    authenticateWithFirebase(account.idToken!!)
                }
                catch(e: Exception){

                }
            }
        }

        binding.tilEmail.editText?.addTextChangedListener { text ->
            binding.btnLogin.isEnabled =  validateEmailPassword(text.toString(),binding.tilPassword.editText?.text.toString())
        }

        binding.tilPassword.editText?.addTextChangedListener { text ->
            binding.btnLogin.isEnabled =  validateEmailPassword(binding.tilEmail.editText?.text.toString(), text.toString())
        }

        binding.btnLogin.setOnClickListener {
           // val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
            //finish()
            val password =  binding.tilPassword.editText?.text.toString()
            val email = binding.tilEmail.editText?.text.toString()

            loginWhitEmailAndPassword(email,password)
        }
        binding.btnGoolge.setOnClickListener {
            loginWhitGoogle()
        }
        binding.btnSignUp.setOnClickListener {

            val password =  binding.tilPassword.editText?.text.toString()
            val email = binding.tilEmail.editText?.text.toString()

            signUpWithEmailAndPassword(email,password)
        }

    }

    private fun loginWhitEmailAndPassword(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                else{
                    Toast.makeText(this,"Usuario No esta registrado",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signUpWithEmailAndPassword(email: String, password: String) {
        if (validateEmailPassword(email,password)){
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        val user = firebaseAuth.currentUser
                        Toast.makeText(this,"Usuario Registrado",Toast.LENGTH_SHORT).show()
                    }
                }
        }
        else{
            Toast.makeText(this,"credenciales invalidas",Toast.LENGTH_SHORT).show()
        }

    }

    private fun authenticateWithFirebase(idToken: String) {
        val authCredentials = GoogleAuthProvider.getCredential(idToken,null)
        firebaseAuth.signInWithCredential(authCredentials)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }
    }

    private fun loginWhitGoogle(){
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_cliente_id))
            .requestEmail()
            .build()

        val googleClient = GoogleSignIn.getClient(this,googleSignInOptions)
        val intent = googleClient.signInIntent
        googleLaucher.launch(intent)
    }

    private fun validateEmailPassword(email:String, password:String):Boolean{

        //val isEmailValid = email == "ejemplo@idat.edu.pe"
        //val isPasswordValid = password == "123456"

        val isEmailValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 6

        return isEmailValid && isPasswordValid
        //return true
    }
}