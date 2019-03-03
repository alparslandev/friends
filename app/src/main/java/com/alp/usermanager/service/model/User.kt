package com.alp.usermanager.service.model

import java.io.Serializable

data class User(
    var id: Int,
    var email: String,
    var last_name: String,
    var image_url: String,
    var first_name: String,
    var birth_day: String,
    var address: String,
    var phone: String) : Serializable {
    fun getNameSurname(): CharSequence? {
        return "$first_name $last_name"
    }
}