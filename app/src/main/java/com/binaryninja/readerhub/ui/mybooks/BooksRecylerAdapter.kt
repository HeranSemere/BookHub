package com.binaryninja.readerhub.ui.mybooks

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.binaryninja.readerhub.OwnersBooks
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.data.User
import kotlinx.android.synthetic.main.book_item_layout.view.*

class BooksRecylerAdapter( private var usersBooks : List<User>): RecyclerView.Adapter<BooksRecylerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val bookPicture: ImageView = itemView.findViewById(R.id.bookPic)
        val bookTitle: TextView = itemView.findViewById(R.id.bookTitle)
        val bookGenre: TextView = itemView.findViewById(R.id.bookGenre)
        val bookPages: TextView = itemView.findViewById(R.id.bookPages)
        val bookAuthor: TextView = itemView.findViewById(R.id.bookAuthor)


        val ownersPicture: ImageView = itemView.findViewById(R.id.ownerProfilePic)
        val ownersName: TextView = itemView.findViewById(R.id.ownerName)
        val ownersRating: ImageView = itemView.findViewById(R.id.rating)


        init {


                val position: Int = adapterPosition

                itemView.owners_profile.setOnClickListener {

                    val intent = Intent(itemView.context, OwnersBooks::class.java)
                    ContextCompat.startActivity(itemView.context, intent, null)

                }
                itemView.book_details.setOnClickListener {

                    val intent = Intent(itemView.context, BookDetail::class.java)
                    ContextCompat.startActivity(itemView.context, intent, null)

                }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.book_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return usersBooks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

     for (i in usersBooks[position].books.indices){

         holder.bookPicture.setImageResource(usersBooks[position].books[i].bookPicture)
         holder.bookTitle.text = usersBooks[position].books[i].bookName
         holder.bookGenre.text = usersBooks[position].books[i].genre
         holder.bookPages.text = "${Integer.toString(usersBooks[position].books[i].page)} Pages"
         holder.bookAuthor.text = "Author: ${usersBooks[position].books[i].author}"


         holder.ownersPicture.setImageResource(usersBooks[position].profilePicture)
         holder.ownersName.text = usersBooks[position].firstName
         holder.ownersRating.setImageResource(usersBooks[position].rating)



     }


    }




}