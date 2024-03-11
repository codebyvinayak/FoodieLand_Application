package com.example.foodie_land

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodie_land.admin.AdminLoginActivity

class ChooseYourRole : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_your_role)

        // Find the button by its ID for User Login
        val userButton = findViewById<Button>(R.id.btnUserLogin)

        // Create an OnClickListener for User Login button
        val userLoginOnClickListener = View.OnClickListener {
            // This code will run when the User Login button is clicked
            // You can perform actions or start activities here
            Toast.makeText(this, "Welcome to User Login", Toast.LENGTH_SHORT).show()

            // Example: Starting the User Login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Set the OnClickListener on the User Login button
        userButton.setOnClickListener(userLoginOnClickListener)

        // Find the button by its ID for Admin Login
        val adminButton = findViewById<Button>(R.id.btnAdminLogin)

        // Create an OnClickListener for Admin Login button
        val adminLoginOnClickListener = View.OnClickListener {
            // This code will run when the Admin Login button is clicked
            // You can perform actions or start activities here
            Toast.makeText(this, "Welcome to Admin Login", Toast.LENGTH_SHORT).show()

            // Example: Starting the Admin Login activity
            val intent = Intent(this, AdminLoginActivity::class.java)
            startActivity(intent)
        }

        // Set the OnClickListener on the Admin Login button
        adminButton.setOnClickListener(adminLoginOnClickListener)
    }
}

