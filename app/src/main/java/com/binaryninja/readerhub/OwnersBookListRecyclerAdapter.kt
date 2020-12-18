package com.binaryninja.readerhub

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.binaryninja.readerhub.data.User
import com.binaryninja.readerhub.ui.mybooks.BookDetail


class OwnersBookListRecyclerAdapter( private var usersBooks : List<User>): RecyclerView.Adapter<OwnersBookListRecyclerAdapter.ViewHolder>(){


inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    val bookPicture: ImageView = itemView.findViewById(R.id.listbookPic)
    val bookTitle: TextView = itemView.findViewById(R.id.listbookTitle)
    val bookGenre: TextView = itemView.findViewById(R.id.listbookGenre)
    val bookPages: TextView = itemView.findViewById(R.id.listbookPages)
    val bookAuthor: TextView = itemView.findViewById(R.id.listbookAuthor)


    init {

        itemView.setOnClickListener {

            val position: Int = adapterPosition

            val intent = Intent(itemView.context, BookDetail::class.java)
            ContextCompat.startActivity(itemView.context, intent, null)
        }
    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_of_owners_books_item_layout, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return usersBooks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        for (i in usersBooks[position].books.indices) {

            holder.bookPicture.setImageResource(usersBooks[position].books[i].bookPicture)
            holder.bookTitle.text = usersBooks[position].books[i].bookName
            holder.bookGenre.text = usersBooks[position].books[i].genre
            holder.bookPages.text = "${Integer.toString(usersBooks[position].books[i].page)} Pages"
            holder.bookAuthor.text = "Author: ${usersBooks[position].books[i].author}"
        }

    }


}