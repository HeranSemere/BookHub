package com.binaryninja.readerhub.data

data class Message(
    val senderPicture: Int,
    val senderName: String,
    val senderMessage: String,
    val book: String,
    val timeStamp: String,
    val tag: Int,
    val newRequest: Boolean
)