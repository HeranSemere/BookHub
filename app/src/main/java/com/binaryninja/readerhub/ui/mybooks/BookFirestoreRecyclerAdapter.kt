package com.binaryninja.readerhub.ui.mybooks

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaryninja.readerhub.OwnersBooks
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.model.Book
import com.binaryninja.readerhub.ui.mybooks.BookFirestoreRecyclerAdapter.BookViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestoreException
import com.squareup.picasso.Picasso

public class BookFirestoreRecyclerAdapter(
    options: FirestoreRecyclerOptions<*>,
    private val progressBar: ProgressBar?
) : FirestoreRecyclerAdapter<Book, BookViewHolder>(options as FirestoreRecyclerOptions<Book>) {
    override fun onError(e: FirebaseFirestoreException) {
        super.onError(e)
        progressBar!!.visibility = View.GONE
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (itemCount == 0) progressBar!!.visibility = View.VISIBLE
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int, model: Book) {
        if (progressBar != null && progressBar.visibility == View.VISIBLE) progressBar.visibility =
            View.GONE
        holder.bookTitle.text = model.bookName!!
        holder.bookAuthor.text = model.author!!
        holder.bookGenre.text = model.genre!!
        holder.bookPages.text = model.page!!.toString()
        if (!model.bookPicPath.isNullOrEmpty()) {
            Picasso.get().load(model.bookPicPath!!).placeholder(R.drawable.obamas_book)
                .into(holder.bookPicture!!)
        }
        holder.ownersName.text = model.ownerName!!
        holder.ownersName.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(
                    holder.itemView.context,
                    OwnersBooks::class.java
                ).putExtra("uid", model.ownerId)
            )
        }
        holder.ownersPicture.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(
                    holder.itemView.context,
                    OwnersBooks::class.java
                ).putExtra("uid", model.ownerId)
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.book_item_layout, parent, false)
        return BookViewHolder(v)
    }

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See [ ] for configuration options.
     *
     */
    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bookPicture: ImageView = itemView.findViewById(R.id.bookPic)
        var bookTitle: TextView = itemView.findViewById(R.id.bookTitle)
        var bookGenre: TextView = itemView.findViewById(R.id.bookGenre)
        var bookPages: TextView = itemView.findViewById(R.id.bookPages)
        var bookAuthor: TextView = itemView.findViewById(R.id.bookAuthor)
        var ownersPicture: ImageView = itemView.findViewById(R.id.ownerProfilePic)
        var ownersName: TextView = itemView.findViewById(R.id.ownerName)

        init {
            itemView.setOnClickListener {
                val model: Book = getItem(adapterPosition);
                val intent = Intent(itemView.context, BookDetail::class.java)
                intent.putExtra("name", model.bookName!!)
                intent.putExtra("genre", model.genre!!)
                intent.putExtra("author", model.author!!)
                intent.putExtra("synopsis", model.synopsis!!)
                intent.putExtra("page", model.page!!)
                intent.putExtra("ownerName", model.ownerName!!)
                intent.putExtra("ownerId", model.ownerId!!)
                intent.putExtra("bookPicPath", model.bookPicPath!!)
                intent.putExtra("date", model.bookPublishedDate!!)
                itemView.context.startActivity(intent)
            }
        }
    }
}