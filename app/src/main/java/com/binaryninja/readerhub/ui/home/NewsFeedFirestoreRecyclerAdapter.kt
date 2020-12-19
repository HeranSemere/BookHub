package com.binaryninja.readerhub.ui.home

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
import com.binaryninja.readerhub.model.NewsFeed
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestoreException
import com.squareup.picasso.Picasso

public class NewsFeedFirestoreRecyclerAdapter(
    options: FirestoreRecyclerOptions<*>,
    private val progressBar: ProgressBar?
) : FirestoreRecyclerAdapter<NewsFeed, NewsFeedFirestoreRecyclerAdapter.NewsFeedViewHolder>(options as FirestoreRecyclerOptions<NewsFeed>) {
    override fun onError(e: FirebaseFirestoreException) {
        super.onError(e)
        progressBar!!.visibility = View.GONE
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (itemCount == 0) progressBar!!.visibility = View.VISIBLE
    }

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int, model: NewsFeed) {
        if (progressBar != null && progressBar.visibility == View.VISIBLE) progressBar.visibility =
            View.GONE
        holder.ownerPic.setImageResource(R.drawable.profile3)
        holder.ownerName.text = model.ownerName!!
        holder.postDate.text = model.date!!
        holder.postContent.text = model.content!!
        if (!model.photoPath.isNullOrEmpty()) {
            holder.postPhoto.visibility = View.VISIBLE
            Picasso.get().load(model.photoPath!!).placeholder(R.drawable.obamas_book)
                .into(holder.postPhoto!!)
        }
        holder.ownerName.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(
                    holder.itemView.context,
                    OwnersBooks::class.java
                ).putExtra("uid", model.ownerId)
            )
        }
        holder.ownerPic.setOnClickListener {
            holder.itemView.context.startActivity(
                Intent(
                    holder.itemView.context,
                    OwnersBooks::class.java
                ).putExtra("uid", model.ownerId)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.post_item_view, parent, false)
        return NewsFeedViewHolder(v)
    }

    inner class NewsFeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ownerPic: ImageView = itemView.findViewById(R.id.post_owner_pic)
        var ownerName: TextView = itemView.findViewById(R.id.post_owner_name)
        var postDate: TextView = itemView.findViewById(R.id.post_date)
        var postContent: TextView = itemView.findViewById(R.id.post_content)
        var postPhoto: ImageView = itemView.findViewById(R.id.post_photo)
    }
}