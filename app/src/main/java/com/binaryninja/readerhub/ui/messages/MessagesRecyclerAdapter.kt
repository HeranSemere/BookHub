package com.binaryninja.readerhub.ui.messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.data.Message
import com.binaryninja.readerhub.data.User


class MessagesRecyclerAdapter( private var messages : List<Message>): RecyclerView.Adapter<MessagesRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val senderPicture: ImageView = itemView.findViewById(R.id.senderImage)
        val senderName: TextView = itemView.findViewById(R.id.senderName)
        val senderMessage: TextView = itemView.findViewById(R.id.senderMessage)
        val timeStamp: TextView = itemView.findViewById(R.id.timeStamp)
        val tag: ImageView= itemView.findViewById(R.id.tag)
        val book: TextView= itemView.findViewById(R.id.senderBook)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.messages_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.senderPicture.setImageResource(messages[position].senderPicture)
            holder.senderName.text = messages[position].senderName
            holder.book.text = messages[position].book
            holder.senderMessage.text = messages[position].senderMessage
            holder.timeStamp.text = messages[position].timeStamp
            holder.tag.setImageResource(messages[position].tag)

    }
}