package com.alp.usermanager.service.model

data class User(
    var id: Int,
    var email: String,
    var last_name: String,
    var image_url: String,
    var first_name: String) {
    fun getNameSurname(): CharSequence? {
        return "$first_name $last_name"
    }
}