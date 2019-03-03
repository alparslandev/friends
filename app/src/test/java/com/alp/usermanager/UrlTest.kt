package com.alp.usermanager

import com.alp.usermanager.utils.ValidationUtils
import org.junit.Assert
import org.junit.Test

class UrlTest {
    @Test
    fun urlValidator_CorrectUrlSimple_ReturnsTrue() {
        Assert.assertTrue(ValidationUtils.isValidUrl("https://github.com/alparslandev"))
    }

    @Test
    fun urlValidator_CorrectUrlSubDomain_ReturnsTrue() {
        Assert.assertTrue(ValidationUtils.isValidUrl("https://www.turkiye.gov.tr/"))
    }

    @Test
    fun urlValidator_InvalidUrlNoDomain_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidUrl("a"))
    }

    @Test
    fun urlValidator_InvalidUrlDoubleDot_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidUrl("https://github..com/alparslandev"))
    }

    @Test
    fun urlValidator_InvalidUrlNoHttps_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidUrl("://github..com/alparslandev"))
    }

    @Test
    fun urlValidator_EmptyString_ReturnsFalse() {
        Assert.assertFalse(ValidationUtils.isValidUrl(""))
    }
}