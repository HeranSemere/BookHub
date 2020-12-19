package com.binaryninja.readerhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.binaryninja.readerhub.model.Book
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_book_app_bar.*
import java.util.*

class AddBook : AppCompatActivity() {
    var title: EditText? = null
    var page: EditText? = null
    var author: EditText? = null
    var synopsis: EditText? = null
    var genre: Spinner? = null
    var addBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        add_book_back.setOnClickListener {
            finish()
        }
        title = findViewById(R.id.add_book_title)
        page = findViewById(R.id.add_book_page)
        author = findViewById(R.id.add_book_author)
        synopsis = findViewById(R.id.add_book_synopsis)
        genre = findViewById(R.id.add_book_genre)
        addBtn = findViewById(R.id.addDoneButton)
        addBtn?.setOnClickListener(View.OnClickListener { view: View? ->
            if (bookData != null) {
                addBook(bookData)
            }
        })

    }

    private val bookData: Book?
        get() {
            val tit: String = title!!.text.toString()
            val pag: String = page!!.text.toString()
            val autho: String = author!!.text.toString()
            val synopsi: String = synopsis!!.text.toString()
            val gene: String = genre!!.selectedItem.toString()
            val user = FirebaseAuth.getInstance().currentUser
            val db = FirebaseFirestore.getInstance();
            var name = user?.displayName
            db.collection("users").document(user?.uid!!).get().addOnCompleteListener(this@AddBook){ task->
                if (task.isSuccessful)
                    name = task.result?.get("name").toString()
            }
            when {
                tit.isEmpty() -> title!!.error =
                    "title Should't be blank "
                autho.isEmpty() -> author!!.error =
                    "title Should't be blank "
                synopsi.isEmpty() -> synopsis!!.error =
                    "title Should't be blank "
                pag.isEmpty() -> page!!.error =
                    "Should't be blank "
                else -> {

                    val id = user!!.uid
                    return Book(
                        id,
                        name,
                        autho,
                        tit + id,
                        tit,
                        "",
                        gene,
                        "",
                        synopsi,
                        true,
                        pag.toInt(),
                        Date().toString()
                    )
                }
            }
            return null
        }

    private fun addBook(book: Book?) {
        val db = FirebaseFirestore.getInstance()
        val reference = db.collection("books").document(book?.bookID!!);
        addBtn!!.text = "saving to server..."
        reference.set(book!!).addOnCompleteListener(this@AddBook) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@AddBook, "Book added Successfully", Toast.LENGTH_SHORT)
                    .show()
                finish()
            } else {
                addBtn!!.text = task.exception!!.message
            }
        }
    }
}