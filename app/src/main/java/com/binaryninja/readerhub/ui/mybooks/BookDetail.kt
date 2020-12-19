package com.binaryninja.readerhub.ui.mybooks

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.binaryninja.readerhub.OwnersBooks
import com.binaryninja.readerhub.R
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.filter_dialoge.view.*
import kotlinx.android.synthetic.main.request_dialoge.view.*

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

        requestAlert.setOnClickListener {
            val filterDialogView= LayoutInflater.from(this).inflate(R.layout.request_dialoge,null)

            val mBuilder= AlertDialog.Builder(this)
                .setView(filterDialogView)

            val mAlertDialog=mBuilder.show()

            filterDialogView.dialogeRequestBackImage.setOnClickListener {

                mAlertDialog.dismiss()
            }

            filterDialogView.requestButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }
}