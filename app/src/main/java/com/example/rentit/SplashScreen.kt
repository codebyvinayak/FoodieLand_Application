package com.example.rentit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        android.os.Handler().postDelayed( {
            val intent = Intent(this@SplashScreen,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}

fun Any.PostDelayed(function: () -> Unit, i: Int) {
}
