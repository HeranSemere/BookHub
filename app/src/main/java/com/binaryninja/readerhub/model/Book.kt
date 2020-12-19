package com.binaryninja.readerhub.model

class Book {
    var ownerId: String? = null
    var ownerName: String? = null
    var author: String? = null
    var bookID: String? = null
    var bookName: String? = null
    var bookPicPath: String? = null
    var genre: String? = null
    var language: String? = null
    var synopsis: String? = null
    var isVisibility = false
    var page = 0
    var bookPublishedDate: String? = null

    constructor(
        ownerId: String?,
        ownerName: String?,
        author: String?,
        bookID: String?,
        bookName: String?,
        bookPicPath: String?,
        genre: String?,
        language: String?,
        synopsis: String?,
        visibility: Boolean,
        page: Int,
        bookPublishedDate: String?
    ) {
        this.ownerId = ownerId
        this.ownerName = ownerName
        this.author = author
        this.bookID = bookID
        this.bookName = bookName
        this.bookPicPath = bookPicPath
        this.genre = genre
        this.language = language
        this.synopsis = synopsis
        isVisibility = visibility
        this.page = page
        this.bookPublishedDate = bookPublishedDate
    }

    constructor() {}
}