package com.binaryninja.readerhub.ui.mybooks

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.data.User
import com.binaryninja.readerhub.model.Book
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.filter_dialoge.*
import kotlinx.android.synthetic.main.filter_dialoge.view.*
import kotlinx.android.synthetic.main.fragment_mybook.*


class MyBooksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_mybook, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val query: Query = FirebaseFirestore.getInstance()
            .collection("books").whereEqualTo("visibility",true);
        val recyclerOptions: FirestoreRecyclerOptions<Book> =
            FirestoreRecyclerOptions.Builder<Book>()
                .setQuery(query, Book::class.java)
                .setLifecycleOwner(this)
                .build()

        books_recyclerView.layoutManager = LinearLayoutManager(activity)
        books_recyclerView.adapter = BookFirestoreRecyclerAdapter(options = recyclerOptions,progressBar = view.findViewById<ProgressBar>(R.id.progress))


        filter.setOnClickListener {
            val filterDialogView= LayoutInflater.from(context).inflate(
                R.layout.filter_dialoge,
                null
            )

            val mBuilder= AlertDialog.Builder(context)
                .setView(filterDialogView)

            val mAlertDialog=mBuilder.show()

            filterDialogView.dialogeBackImage.setOnClickListener {

                mAlertDialog.dismiss()
            }

            filterDialogView.saveFilterButton.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }
    }
}