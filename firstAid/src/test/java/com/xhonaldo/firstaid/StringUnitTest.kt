package com.xhonaldo.firstaid

import com.xhonaldo.firstaid.extensions.containsDigit
import com.xhonaldo.firstaid.extensions.empty
import com.xhonaldo.firstaid.extensions.hextoRGB
import com.xhonaldo.firstaid.extensions.ifNotEmpty
import com.xhonaldo.firstaid.extensions.isAlphanumeric
import com.xhonaldo.firstaid.extensions.isPhoneNumber
import com.xhonaldo.firstaid.extensions.isUrl
import com.xhonaldo.firstaid.extensions.isValidEmail
import com.xhonaldo.firstaid.extensions.removeAllWhitespaces
import com.xhonaldo.firstaid.extensions.removeDuplicateWhitespaces
import com.xhonaldo.firstaid.extensions.toDate
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.util.Calendar

class StringUnitTest {
    @Test
    fun testStringEmpty() {
        assertEquals("", String.empty())
    }

    @Test
    fun testisValidEmail() {
        val regex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        assertTrue("test@example.com".isValidEmail(regex))
        assertFalse("bdss".isValidEmail(regex))
    }


    @Test
    fun testIsUrl() {
        assertTrue("https://www.example.com".isUrl())
        assertFalse("invalid-url".isUrl())
    }

    @Test
    fun testIsPhoneNumber() {
        val regex = Regex("^\\+(?:[0-9] ?){6,14}[0-9]$")
        assertTrue("+1234567890".isPhoneNumber(regex))
        assertFalse("adsaa".isPhoneNumber(regex))
    }


    @Test
    fun testContainsDigit() {
        assertTrue("abc123".containsDigit)
        assertFalse("asdasd".containsDigit)
    }

    @Test
    fun testIsAlphanumeric() {
        assertTrue("abc123".isAlphanumeric)
        assertTrue("Abc123".isAlphanumeric)
        assertFalse("abc 123".isAlphanumeric)
        assertFalse("abc-123".isAlphanumeric)
        assertFalse("".isAlphanumeric)
    }

    @Test
    fun testBase64ToBitmap() {
        // You can write tests for this function if needed
    }

    @Test
    fun testIfNotEmpty() {
        var executed = false
        "".ifNotEmpty { executed = true }
        assertFalse(executed)
        "Hello".ifNotEmpty { executed = true }
        assertTrue(executed)
    }

    @Test
    fun testHexToRGB() {
        assertEquals(Triple("255", "0", "0"), "FF0000".hextoRGB())
        assertEquals(Triple("0", "255", "0"), "00FF00".hextoRGB())
        assertEquals(Triple("0", "0", "255"), "0000FF".hextoRGB())
    }

    @Test
    fun testToDate() {
        val dateStr = "2024/03/22 12:00"
        val expectedDate = Calendar.getInstance().apply {
            set(2024, Calendar.MARCH, 22, 12, 0)
        }.time
        assertEquals(expectedDate, dateStr.toDate())
    }

    @Test
    fun testRemoveAllWhitespaces() {
        assertEquals("HelloWorld", "Hello World".removeAllWhitespaces())
        assertEquals("12345", " 1 2 3 4 5 ".removeAllWhitespaces())
    }

    @Test
    fun testRemoveDuplicateWhitespaces() {
        assertEquals("Hello World", "Hello  World".removeDuplicateWhitespaces())
        assertEquals("1 2 3 4 5", "1   2  3  4   5".removeDuplicateWhitespaces())
    }
}