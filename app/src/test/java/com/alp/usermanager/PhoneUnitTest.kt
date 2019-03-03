package com.alp.usermanager

import com.alp.usermanager.utils.ValidationUtils
import org.junit.Assert
import org.junit.Test

class PhoneUnitTest {

    @Test
    fun phoneValidator_ValidPhoneReturnsTrue() {
        Assert.assertTrue(ValidationUtils.isValidPhoneInTurkey("905373473201"))
    }

    @Test
    fun phoneValidator_InvalidPhoneMissingCharacter_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidEmail("90537347320"))
    }

    @Test
    fun phoneValidator_InvalidPhoneStringContainsLetter_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidEmail("9053734732o1"))
    }

    @Test
    fun phoneValidator_InvalidPhoneStringNotNumeric_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidEmail("sıfır besyuz"))
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidEmail(""))
    }

}