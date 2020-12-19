package com.binaryninja.readerhub.model

class NewsFeed {
    var ownerId: String? = null
    var ownerName: String? = null
    var postId: String? = null
    var content: String? = null
    var photoPath: String? = null
    var date: String? = null

    constructor(
        ownerId: String?,
        ownerName :String?,
        postId: String?,
        content: String?,
        photoPath: String?,
        date: String?
    ) {
        this.ownerId = ownerId
        this.ownerName = ownerName
        this.postId = postId
        this.content = content
        this.photoPath = photoPath
        this.date = date
    }

    constructor() {}
}