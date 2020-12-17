package com.binaryninja.readerhub.model

class User {
    var id: String? = null
    var name: String? = null
    var username: String? = null
    var password: String? = null
    var address: String? = null

    constructor(
        id: String?,
        name: String?,
        username: String?,
        password: String?,
        address: String?
    ) {
        this.id = id
        this.name = name
        this.username = username
        this.password = password
        this.address = address
    }

    constructor() {}
}