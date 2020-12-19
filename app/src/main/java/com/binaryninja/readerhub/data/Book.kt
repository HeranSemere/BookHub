package com.binaryninja.readerhub.data

import android.net.Uri

data class Book (
    //val bookPicture: Uri,
    val bookPicture: Int,
    val bookName: String,
    val genre: String,
    val page: Int,
    val author: String,
    val synopsis :String,
    val visibility: Boolean
    )