package com.binaryninja.readerhub.ui.mybooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.data.Book
import com.binaryninja.readerhub.data.User
import kotlinx.android.synthetic.main.fragment_mybook.*

class MyBooksFragment : Fragment() {

    private lateinit var myBookViewModel: MyBooksViewModel

    private var usersBooks = mutableListOf<User>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_mybook, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val book1= Book(R.drawable.obamas_book,"A Promised Land", "Memoir",700,"Barack Obama", R.string.a_promised_land_synopsis.toString(),true)

        val books= listOf(book1,book1)

        for(i in 0..10) {
            usersBooks.add(User(R.drawable.profile_pic,"Sarah","Assefa Tadesse",R.drawable.rating,books))
        }

        books_recyclerView.layoutManager = LinearLayoutManager(activity)
        books_recyclerView.adapter = BooksRecylerAdapter(usersBooks)
    }
}