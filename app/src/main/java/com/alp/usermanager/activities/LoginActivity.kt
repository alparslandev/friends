package com.alp.usermanager.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.alp.usermanager.R
import com.alp.usermanager.wrappers.TextWatcherWrapper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.button_primary.*
import android.widget.Toast
import com.alp.usermanager.service.Api
import com.alp.usermanager.service.IDataService
import com.alp.usermanager.service.request.LoginRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btn_primary.isEnabled = false

        et_mail.addTextChangedListener(object : TextWatcherWrapper() {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkButtonAvailability()
            }
        })

        et_password.addTextChangedListener(object : TextWatcherWrapper() {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkButtonAvailability()
            }
        })

        btn_primary.setOnClickListener {
            run {
                Api.getDataClient().create(IDataService::class.java)
                    .login(LoginRequest(et_mail.text.toString(), et_password.text.toString()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { user -> /*refreshUser(user)*/ },
                        { error -> showError(error.message.toString()) })
            }
        }
    }

    fun showError(msg : String) { // TODO Move this to BaseActivity
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun checkButtonAvailability() {
        var isEnabled = getMailConditions() && getPasswordConditions()

        btn_primary.isEnabled = isEnabled
    }

    private fun getMailConditions(): Boolean {
        val text = et_mail.text
        return !TextUtils.isEmpty(text) && text.endsWith(".com")
    }

    private fun getPasswordConditions(): Boolean {
        val text = et_password.text
        return !TextUtils.isEmpty(text) && text.length > 4
    }
}
