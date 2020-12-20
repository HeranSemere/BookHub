package com.binaryninja.readerhub.ui.mybooks

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.binaryninja.readerhub.OwnersBooks
import com.binaryninja.readerhub.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.request_dialoge.view.*

class BookDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        try {
            detail_name.text = intent.getStringExtra("name")
            detail_genre.text = "Genre : " + intent.getStringExtra("genre")
            detail_author.text = "Author " + intent.getStringExtra("author")
            detail_synopsis.text = intent.getStringExtra("synopsis")
            detail_ownername.text = intent.getStringExtra("ownerName")
            detail_page.text =
                "Total Page : " + intent.getIntExtra("page", 300.rangeTo(500).random())
            val uid = intent.getStringExtra("ownerId")
            val picpath = intent.getStringExtra("bookPicPath")
            if (!picpath.isNullOrBlank()) {
                Picasso.get().load(picpath).placeholder(R.drawable.obamas_book)
                    .into(detail_book_cover)
            }

            backBookDetail.setOnClickListener {
                finish()
            }
            ownerProfile.setOnClickListener {
                val intent = Intent(this, OwnersBooks::class.java).putExtra("uid", uid)
                ContextCompat.startActivity(this, intent, null)
            }

            detail_send_request.setOnClickListener {
                val filterDialogView =
                    LayoutInflater.from(this).inflate(R.layout.request_dialoge, null)

                val mBuilder = AlertDialog.Builder(this)
                    .setView(filterDialogView)

                val mAlertDialog = mBuilder.show()

                filterDialogView.dialogeRequestBackImage.setOnClickListener {

                    mAlertDialog.dismiss()
                }

                filterDialogView.requestButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error : " + e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}