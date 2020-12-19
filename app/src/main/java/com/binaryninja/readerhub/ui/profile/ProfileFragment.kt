package com.binaryninja.readerhub.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaryninja.readerhub.AddBook
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.model.Book
import com.binaryninja.readerhub.ui.mybooks.BookFirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_mybook.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query: Query = FirebaseFirestore.getInstance()
            .collection("books").whereEqualTo("ownerId",FirebaseAuth.getInstance().currentUser?.uid!!);
        val recyclerOptions: FirestoreRecyclerOptions<Book> =
            FirestoreRecyclerOptions.Builder<Book>()
                .setQuery(query, Book::class.java)
                .setLifecycleOwner(this)
                .build()
        owners_books_recyclerView.layoutManager = LinearLayoutManager(activity)
        owners_books_recyclerView.adapter = OwnerBookFirestoreRecyclerAdapter(recyclerOptions,view.findViewById(R.id.owner_progress))
        addFAB.setOnClickListener {

            val intent = Intent(activity, AddBook::class.java)
            activity?.startActivity(intent)

        }
    }
}