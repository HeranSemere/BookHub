package com.binaryninja.readerhub.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.model.NewsFeed
import com.binaryninja.readerhub.tools.SharedPref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.create_post_dialog.*
import java.util.*

class CreatePostDialog : AppCompatActivity() {
    lateinit var name:String;
    lateinit var user:FirebaseUser;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_post_dialog)
        user = FirebaseAuth.getInstance().currentUser!!;
        name = "${user?.displayName}  ${user?.uid?.subSequence(0, 10)}"
        create_post_ownername.text = SharedPref(this).getString("username", "Create post")
        FirebaseFirestore.getInstance().collection("users").document(user?.uid!!).get()
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful)
                    name = task.result?.get("name").toString()
            }
        create_post_createbtn.setOnClickListener {
            if (validatePost() != null)
                savePost()
        }
    }

    private fun validatePost(): NewsFeed? {
        val content: String = create_post_content.text.toString();
        val cal = GregorianCalendar();
        val date: String = "${cal.get(Calendar.HOUR)}:${cal.get(Calendar.MINUTE)} ${
            cal.getDisplayName(
                Calendar.AM_PM,
                Calendar.SHORT,
                Locale.getDefault()
            )
        } ${
            cal.getDisplayName(
                Calendar.MONTH,
                Calendar.SHORT,
                Locale.getDefault()
            )
        } ${cal.get(Calendar.DAY_OF_MONTH)}"
        if (content.isEmpty())
            create_post_content.error = "Post should not be blank"
        else {
            return NewsFeed(user?.uid!!, name, " ", content, "", date)
        }
        return null;
    }

    private fun savePost() {
        create_post_createbtn.text = "Posting..."
        FirebaseFirestore.getInstance().collection("posts").add(validatePost()!!)
            .addOnCompleteListener(this@CreatePostDialog) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Post Created", Toast.LENGTH_SHORT).show()
                    finish();
                }
                else{
                    create_post_createbtn.text = "Try Again :("
                    Toast.makeText(this, "Try Again ${task.exception?.message}", Toast.LENGTH_SHORT)
                        .show()

            }}
    }
}