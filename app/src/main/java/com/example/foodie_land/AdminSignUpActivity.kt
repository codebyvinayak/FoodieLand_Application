package com.example.foodie_land.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodie_land.databinding.ActivityAdminSignUpBinding

class AdminSignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAdminSignUpBinding
    private lateinit var databaseHelperAdmin : DatabaseHelperAdmin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelperAdmin = DatabaseHelperAdmin(this)

        binding.signupButtonAdmin.setOnClickListener{
            val signupAdmin = binding.signupAdmin.text.toString().trim()
            val signupPass = binding.signupPass.text.toString().trim()

            if (signupAdmin.isEmpty() || signupPass.isEmpty()) {
                // Show an error message if either field is empty
                Toast.makeText(this, "Both fields are required", Toast.LENGTH_SHORT).show()
            } else {
                // Attempt to log in only if both fields are not empty
                signupDatabase(signupAdmin, signupPass)
            }
        }

        binding.loginRedirect.setOnClickListener{
            val intent = Intent(this, AdminLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun onAdminLoginClick(view: View) {
        val intent = Intent(this, AdminLoginActivity::class.java)
        startActivity(intent)
    }

    private fun signupDatabase(admin: String, pass: String){
        val insertRowId = databaseHelperAdmin.insertAdmin(admin, pass)
        if (insertRowId != -1L){
             Toast.makeText(this,"Signup Successful", Toast.LENGTH_SHORT).show()
             val intent = Intent(this, AdminLoginActivity::class.java)
             startActivity(intent)
             finish()
        }   else  {
            Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
        }
    }
}