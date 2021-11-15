package com.example.ecomercesystem.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ecomercesystem.MainActivity
import com.example.ecomercesystem.R
import com.example.ecomercesystem.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        var viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnLogin.setOnClickListener {
            viewModel.isUserValid()
            if (viewModel.isUserValid.value == true)
            {
                Log.d("LoginActLog", "Valid")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_LONG).show()
                Log.d("LoginActLog", "Invalid")
            }
        }
    }
}