package com.binaryninja.readerhub.ui.mybooks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.binaryninja.readerhub.OwnersBooks
import com.binaryninja.readerhub.R
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)


        backBookDetail.setOnClickListener {
            finish()
        }
        ownerProfile.setOnClickListener {
            val intent = Intent(this, OwnersBooks::class.java)
            ContextCompat.startActivity(this, intent, null)
        }
    }
}