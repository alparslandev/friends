package com.alp.usermanager.utils

import java.lang.Double.parseDouble
import java.util.regex.Pattern

class ValidationUtils {

    companion object {
        val EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+")!!

        val TURKISH_PHONE_PREFIX = "5"
        val TURKEY_PHONE_CODE = "90"

        fun isValidEmail(email : String) : Boolean {
            return EMAIL_PATTERN.matcher(email).matches()
        }

        fun isValidPhoneInTurkey(phone : String) : Boolean {
            var numeric = true

            try {
                val num = parseDouble(phone)
            } catch (e: NumberFormatException) {
                numeric = false
            }
            return phone.length == 12 && numeric && phone.startsWith(TURKEY_PHONE_CODE) && phone.substring(2).startsWith(TURKISH_PHONE_PREFIX)
        }
    }
}
