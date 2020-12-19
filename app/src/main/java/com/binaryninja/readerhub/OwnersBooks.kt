package com.binaryninja.readerhub

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaryninja.readerhub.ui.profile.OwnerBookFirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_owners_books.*

class OwnersBooks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owners_books)
        try {
            var id = intent.getStringExtra("uid");
            if (id.isNullOrBlank()) id = FirebaseAuth.getInstance().currentUser?.uid
            FirebaseFirestore.getInstance().collection("users").document(id!!).get()
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser;
                        val tname = if (user?.displayName !=null) user?.displayName else user?.uid?.substring(0,10)
                        val name = if (task.result?.getString("name")==null) tname else task.result?.getString("name")
                        profile_owner_name.text = name
                        profile_owned_by.text =
                            "Books Owned By ${name}"
                    }
                }

            val query: Query = FirebaseFirestore.getInstance()
                .collection("books").whereEqualTo("ownerId", id);
            val recyclerOptions: FirestoreRecyclerOptions<com.binaryninja.readerhub.model.Book> =
                FirestoreRecyclerOptions.Builder<com.binaryninja.readerhub.model.Book>()
                    .setQuery(query, com.binaryninja.readerhub.model.Book::class.java)
                    .setLifecycleOwner(this)
                    .build()

            owners_list_of_books_recyclerView.layoutManager = LinearLayoutManager(this)
            owners_list_of_books_recyclerView.adapter =
                OwnerBookFirestoreRecyclerAdapter(recyclerOptions, findViewById(R.id.progress))
        } catch (e: Exception) {
            Toast.makeText(this, "Error : " + e.message, Toast.LENGTH_SHORT).show()
        }
    }
}