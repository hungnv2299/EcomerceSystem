package com.example.ecomercesystem.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    val isUserValid : MutableLiveData<Boolean> = MutableLiveData(false)
    val username : MutableLiveData<String> = MutableLiveData()
    val password : MutableLiveData<String> = MutableLiveData()

    fun isUserValid(){
        isUserValid.value = !(username.value.isNullOrEmpty() || password.value.isNullOrEmpty())
    }


}