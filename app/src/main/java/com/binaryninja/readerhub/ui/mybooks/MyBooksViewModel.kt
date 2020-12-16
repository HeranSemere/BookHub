package com.binaryninja.readerhub.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyBooksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is my book Fragment"
    }
    val text: LiveData<String> = _text
}