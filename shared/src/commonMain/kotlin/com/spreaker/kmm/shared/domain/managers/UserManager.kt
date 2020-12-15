package com.spreaker.kmm.shared.domain.managers


data class User(val userId: Int, val fullname: String?)

class UserManager {
    var loggedUser: User? = null
}