package com.binaryninja.readerhub.ui.messages

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.binaryninja.readerhub.OwnersBooks
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.data.Message
import com.binaryninja.readerhub.data.User
import kotlinx.android.synthetic.main.messages_item_layout.view.*


class MessagesRecyclerAdapter( private var messages : List<Message>): RecyclerView.Adapter<MessagesRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val senderPicture: ImageView = itemView.findViewById(R.id.senderImage)
        val senderName: TextView = itemView.findViewById(R.id.senderName)
        val senderMessage: TextView = itemView.findViewById(R.id.senderMessage)
        val timeStamp: TextView = itemView.findViewById(R.id.timeStamp)
        val tag: ImageView= itemView.findViewById(R.id.tag)
        val book: TextView= itemView.findViewById(R.id.senderBook)

        val message_item:LinearLayout=itemView.findViewById(R.id.message_item)
        val request_item:LinearLayout=itemView.findViewById(R.id.request_item)


        val senderPictureRequest: ImageView = itemView.findViewById(R.id.senderImageRequest)
        val senderNameRequest: TextView = itemView.findViewById(R.id.senderNameRequest)
        val senderBookRequest: TextView = itemView.findViewById(R.id.senderBookRequest)
        val timeStampRequest: TextView = itemView.findViewById(R.id.timeStampRequest)


        init {

            itemView.senders_profile.setOnClickListener {

                val intent = Intent(itemView.context, OwnersBooks::class.java)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.messages_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            if(messages[position].newRequest){
                holder.message_item.visibility = View.GONE
                holder.senderPictureRequest.setImageResource(messages[position].senderPicture)
                holder.senderNameRequest.text = messages[position].senderName
                holder.senderBookRequest.text = messages[position].book
                holder.timeStampRequest.text = messages[position].timeStamp


            }else{
                holder.request_item.visibility = View.GONE
                holder.senderPicture.setImageResource(messages[position].senderPicture)
                holder.senderName.text = messages[position].senderName
                holder.book.text = messages[position].book
                holder.senderMessage.text = messages[position].senderMessage
                holder.timeStamp.text = messages[position].timeStamp
                holder.tag.setImageResource(messages[position].tag)
            }

    }
}