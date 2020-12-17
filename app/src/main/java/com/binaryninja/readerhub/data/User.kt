package com.binaryninja.readerhub.data

import android.net.Uri

data class User (
    //val profilePicture: Uri,
    val profilePicture: Int,
    val firstName: String,
    val lastName: String,
    val rating: Int,
    val books: List<Book>


)