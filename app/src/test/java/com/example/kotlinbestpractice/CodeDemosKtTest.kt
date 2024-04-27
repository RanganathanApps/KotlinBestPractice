package com.example.kotlinbestpractice

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CodeDemosKtTest {

    @Test
    fun main() {

        assertEquals("IN",getCountryCode("India"))
        val countryCodeUS = getCountryCode("United States")
        assertEquals("US", countryCodeUS)

        val countryCodeNull = getCountryCode("Japan")
        assertNull(countryCodeNull)
    }

}