package com.alp.usermanager

import com.alp.usermanager.utils.ValidationUtils
import org.junit.Assert
import org.junit.Test

class EmailUnitTest {
    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        Assert.assertTrue(ValidationUtils.isValidEmail("name@email.com"))
    }

    @Test
    fun emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        Assert.assertTrue(ValidationUtils.isValidEmail("name@email.co.uk"))
    }

    @Test
    fun emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidEmail("name@email"))
    }

    @Test
    fun emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidEmail("name@email..com"))
    }

    @Test
    fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidEmail("@email.com"))
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidEmail(""))
    }
}