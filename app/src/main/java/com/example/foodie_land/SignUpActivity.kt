package com.example.foodie_land

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodie_land.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.signupButton.setOnClickListener{
            val signupUsername = binding.signupUsername.text.toString().trim()
            val signupPassword = binding.signupPassword.text.toString().trim()

            if (signupUsername.isEmpty() || signupPassword.isEmpty()) {
                // Show an error message if either field is empty
                Toast.makeText(this, "Both fields are required", Toast.LENGTH_SHORT).show()
            } else {
                // Attempt to log in only if both fields are not empty
                signupDatabase(signupUsername, signupPassword)
            }

//            signupDatabase(signupUsername, signupPassword)
        }

        binding.loginRedirect.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun onUserLoginClick(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun signupDatabase(username: String, password: String){
        val insertRowId = databaseHelper.insertUser(username, password)
        if (insertRowId != -1L){
              Toast.makeText(this,"Signup Successful", Toast.LENGTH_SHORT).show()
              val intent = Intent(this, LoginActivity::class.java)
              startActivity(intent)
              finish()
        }   else  {
            Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
        }
    }
}