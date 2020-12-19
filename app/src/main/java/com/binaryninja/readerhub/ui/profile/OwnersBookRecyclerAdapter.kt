package com.binaryninja.readerhub.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.data.User


class OwnersBookRecyclerAdapter( private var usersBooks : List<User>): RecyclerView.Adapter<OwnersBookRecyclerAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val bookPicture: ImageView = itemView.findViewById(R.id.ownerbookpic)
        val bookTitle: TextView = itemView.findViewById(R.id.ownerobokname)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.profile_books_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return usersBooks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        for (i in usersBooks[position].books.indices){

            holder.bookPicture.setImageResource(usersBooks[position].books[i].bookPicture)
            holder.bookTitle.text = usersBooks[position].books[i].bookName
        }
    }


}