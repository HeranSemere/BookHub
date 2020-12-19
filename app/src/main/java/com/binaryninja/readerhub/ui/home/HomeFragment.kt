package com.binaryninja.readerhub.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.model.Book
import com.binaryninja.readerhub.model.NewsFeed
import com.binaryninja.readerhub.ui.mybooks.BookFirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mybook.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val query: Query = FirebaseFirestore.getInstance()
            .collection("posts")
        val recyclerOptions: FirestoreRecyclerOptions<NewsFeed> =
            FirestoreRecyclerOptions.Builder<NewsFeed>()
                .setQuery(query, NewsFeed::class.java)
                .setLifecycleOwner(this)
                .build()

        home_recyclerview.layoutManager = LinearLayoutManager(activity)
        home_recyclerview.adapter = NewsFeedFirestoreRecyclerAdapter(options = recyclerOptions,progressBar = view.findViewById<ProgressBar>(R.id.progress))
        new_post_fab.setOnClickListener { startActivity(Intent(this.context,CreatePostDialog::class.java)) }
    }

}