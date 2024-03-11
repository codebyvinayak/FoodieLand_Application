package com.example.foodie_land.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodie_land.R
import com.example.foodie_land.databinding.ActivityAdminLoginBinding
import com.example.foodie_land.fragment.AdminMainActivity

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminLoginBinding
    private lateinit var databaseHelperAdmin: DatabaseHelperAdmin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelperAdmin =  DatabaseHelperAdmin(this)

        binding.loginButton.setOnClickListener{
            val loginAdmin = binding.loginAdmin.text.toString().trim()
            val loginPass = binding.loginPass.text.toString().trim()

            if (loginAdmin.isEmpty() || loginPass.isEmpty()) {
                // Show an error message if either field is empty
                Toast.makeText(this, "Both fields are required", Toast.LENGTH_SHORT).show()
            } else {
                // Attempt to log in only if both fields are not empty
                loginDatabase(loginAdmin, loginPass)
            }

        }

        binding.signupRedirect.setOnClickListener {
            val intent = Intent(this, AdminSignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginDatabase(Admin: String, pass: String){
        val userExists = databaseHelperAdmin.readAdmin(Admin, pass)
        if (userExists){
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AdminMainActivity::class.java)
            startActivity(intent)
            finish()
        }  else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}