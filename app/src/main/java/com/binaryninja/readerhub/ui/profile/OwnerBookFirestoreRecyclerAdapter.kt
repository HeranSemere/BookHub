package com.binaryninja.readerhub.ui.profile

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.model.Book
import com.binaryninja.readerhub.ui.mybooks.BookDetail
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.android.synthetic.main.profile_books_item_layout.view.*

public class OwnerBookFirestoreRecyclerAdapter(
    options: FirestoreRecyclerOptions<*>,
    private val progressBar: ProgressBar?
) : FirestoreRecyclerAdapter<Book, OwnerBookFirestoreRecyclerAdapter.BookViewHolder>(options as FirestoreRecyclerOptions<Book>) {
    override fun onError(e: FirebaseFirestoreException) {
        super.onError(e)
        progressBar!!.visibility = View.GONE
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int, model: Book) {
        if (progressBar != null && progressBar.visibility == View.VISIBLE) progressBar.visibility =
            View.GONE
        holder.bookTitle.text = model.bookName
        holder.visibility.isChecked = model.isVisibility
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.profile_books_item_layout, parent, false)

        return BookViewHolder(v)
    }

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See [ ] for configuration options.
     *
     */
    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bookPicture: ImageView = itemView.findViewById(R.id.ownerbookpic)
        var bookTitle: TextView = itemView.findViewById(R.id.ownerobokname)
        var visibility: AppCompatCheckBox = itemView.findViewById(R.id.owner_visibility_chxbox)
        var deletebtn: AppCompatImageView = itemView.findViewById(R.id.owner_delete_btn)

        init {
            try {
                bookTitle.setOnClickListener {
                    bookDetailIntent(itemView.context, adapterPosition)
                }
                bookPicture.setOnClickListener {
                    bookDetailIntent(itemView.context, adapterPosition)
                }
                deletebtn.setOnClickListener {
                    deleteBook(itemView, getItem(adapterPosition).bookID!!)
                }
                visibility.setOnCheckedChangeListener { _, b ->
                    changeBookVisibility(itemView, b, getItem(adapterPosition).bookID!!)
                }
            } catch (e: Exception) {
                Log.e("Error ", e.toString())
            }
        }
    }

    private fun bookDetailIntent(context: Context, position: Int) {
        val model: Book = getItem(position);
        val intent = Intent(context, BookDetail::class.java)
        intent.putExtra("name", model.bookName!!)
        intent.putExtra("genre", model.genre!!)
        intent.putExtra("author", model.author!!)
        intent.putExtra("synopsis", model.synopsis!!)
        intent.putExtra("page", model.page!!)
        intent.putExtra("ownerName", model.ownerName!!)
        intent.putExtra("ownerId", model.ownerId!!)
        intent.putExtra("language", model.language!!)
        intent.putExtra("date", model.bookPublishedDate!!)
        intent.putExtra("bookPicPath", model.bookPicPath!!)

        context.startActivity(intent)
    }

    fun changeBookVisibility(itemView: View, visibility: Boolean, id: String) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        itemView.owner_visibility_chxbox.isEnabled = false;
        db.collection("books").document(id).update("visibility", visibility)
            .addOnCompleteListener { task ->
                itemView.owner_visibility_chxbox.isEnabled = true;
                if (task.isSuccessful) {
                    Toast.makeText(
                        itemView.context,
                        " ${if (visibility) "Visible" else "Hidden"} to Public",
                        Toast.LENGTH_SHORT
                    ).show()
                } else Toast.makeText(itemView.context, task.exception?.message, Toast.LENGTH_SHORT)
                    .show()
            };
    }

    fun deleteBook(itemView: View, id: String) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        itemView.owner_delete_btn.isEnabled = false;
        db.collection("books").document(id).delete().addOnCompleteListener { task ->
            if (task.isSuccessful)
                Toast.makeText(
                    itemView.context,
                    "Deleted Successfully",
                    Toast.LENGTH_SHORT
                ).show()
            else Toast.makeText(itemView.context, task.exception?.message, Toast.LENGTH_SHORT)
        }

    }

}
