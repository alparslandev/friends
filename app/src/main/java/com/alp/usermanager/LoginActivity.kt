package com.alp.usermanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.alp.usermanager.wrappers.TextWatcherWrapper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.button_primary.*

abstract class LoginActivity : AppCompatActivity() {

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

        // todo setOnClickListener will be added

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
