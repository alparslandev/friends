package com.alp.usermanager.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import com.alp.usermanager.R
import com.alp.usermanager.service.Api
import com.alp.usermanager.service.IDataService
import com.alp.usermanager.service.model.User
import com.alp.usermanager.wrappers.TextWatcherWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_friend_details.*

class FriendDetailsActivity : AppCompatActivity() {

    var user : User? = null

    companion object {
        val EXTRA_FRIEND = "EXTRA_FRIEND"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_details)

        var bundle : Bundle? = intent.extras
        user = bundle!!.getSerializable(EXTRA_FRIEND) as User

        getUserDetails(user!!)

        initialize(user!!) // Todo remove this.

        et_address.addTextChangedListener(object : TextWatcherWrapper() {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                invalidateOptionsMenu()
            }
        })

        et_phone.addTextChangedListener(object : TextWatcherWrapper() {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                invalidateOptionsMenu()
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val phone = et_phone.text
        val address = et_address.text
        menu.findItem(R.id.action_save).isEnabled = phone.length == 12 && phone.toString() != user?.phone && !address.isEmpty()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.save, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items

        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("CheckResult")
    private fun getUserDetails(user : User) {
        Api.getDataClient().create(IDataService::class.java)
            .getUser(user.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { userDetailed -> initialize(user) },
                { error -> /*showError(error.message.toString())*/ })
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