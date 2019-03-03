package com.alp.usermanager.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.alp.usermanager.R
import com.alp.usermanager.service.model.User
import kotlinx.android.synthetic.main.activity_friend_details.*

class FriendDetailsActivity : AppCompatActivity() {

    companion object {
        val EXTRA_FRIEND = "EXTRA_FRIEND"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_details)

        var bundle : Bundle? = intent.extras
        var user = bundle!!.getSerializable(EXTRA_FRIEND) as User

        initialize(user) // Todo remove this.

    }

    private fun initialize(user: User) {
        siv_avatar.loadUrl(user.image_url)
        tv_name_surname.text = user.getNameSurname()
        tv_birthday.text = user.birth_day
        tv_email.text = user.email
        et_phone.text = user.phone.toEditable()
        et_address.text = user.address.toEditable()
    }
}

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)