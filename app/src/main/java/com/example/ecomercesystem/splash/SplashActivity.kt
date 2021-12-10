package com.example.ecomercesystem.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.ecomercesystem.login.LoginActivity
import com.example.ecomercesystem.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable { /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this, LoginActivity::class.java)
            this.startActivity(mainIntent)
            this.finish()
        }, 2000)
    }
}