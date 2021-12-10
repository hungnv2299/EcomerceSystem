package com.example.ecomercesystem.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val demo = MutableLiveData<String>()
    fun demoVM(){
        demo.value = "test"
    }
    init {
        demoVM()
    }
}