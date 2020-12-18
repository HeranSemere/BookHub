package com.binaryninja.readerhub.ui.mybooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaryninja.readerhub.R
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)


        backBookDetail.setOnClickListener {
            finish()
        }
    }
}