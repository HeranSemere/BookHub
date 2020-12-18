package com.binaryninja.readerhub.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaryninja.readerhub.AddBook
import com.binaryninja.readerhub.OwnersBooks
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.data.Book
import com.binaryninja.readerhub.data.User
import com.binaryninja.readerhub.ui.mybooks.BooksRecylerAdapter
import com.binaryninja.readerhub.ui.mybooks.MyBooksViewModel
import kotlinx.android.synthetic.main.fragment_mybook.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: MyBooksViewModel

    private var usersBooks = mutableListOf<User>()

    override fun onCreateView(

            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        /* profileViewModel =
                ViewModelProviders.of(this).get(MyBooksViewModel::class.java)

        val textView: TextView = root.findViewById(R.id.text)
        profileViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/



        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book1= Book(R.drawable.obamas_book,"A Promised Land", "Memoir",700,"Barack Obama", R.string.a_promised_land_synopsis.toString(),true)

        val books= listOf(book1,book1)

        for(i in 0..10) {
            usersBooks.add(User(R.drawable.profile_pic,"Sarah","Assefa Tadesse",R.drawable.rating,books))
        }

        owners_books_recyclerView.layoutManager = LinearLayoutManager(activity)
        owners_books_recyclerView.adapter = OwnersBookRecyclerAdapter(usersBooks)

        addFAB.setOnClickListener {

            val intent = Intent (activity, AddBook::class.java)
            activity?.startActivity(intent)

        }
    }
}