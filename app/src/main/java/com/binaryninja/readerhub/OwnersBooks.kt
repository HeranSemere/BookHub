package com.binaryninja.readerhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaryninja.readerhub.data.Book
import com.binaryninja.readerhub.data.User
import com.binaryninja.readerhub.ui.mybooks.BooksRecylerAdapter
import kotlinx.android.synthetic.main.activity_owners_books.*
import kotlinx.android.synthetic.main.fragment_mybook.*

class OwnersBooks : AppCompatActivity() {

    private var usersBooks = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owners_books)

        ownersBooksBack.setOnClickListener {
            finish()
        }


        val book1= Book(R.drawable.obamas_book,"A Promised Land", "Memoir",700,"Barack Obama", R.string.a_promised_land_synopsis.toString(),true)

        val books= listOf(book1,book1)

        for(i in 0..10) {
            usersBooks.add(User(R.drawable.profile_pic,"Sarah","Assefa Tadesse",R.drawable.rating,books))
        }

        owners_list_of_books_recyclerView.layoutManager = LinearLayoutManager(this)
        owners_list_of_books_recyclerView.adapter = OwnersBookListRecyclerAdapter(usersBooks)



    }
}