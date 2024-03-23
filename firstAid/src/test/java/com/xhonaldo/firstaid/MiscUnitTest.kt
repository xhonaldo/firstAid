package com.xhonaldo.firstaid

import com.xhonaldo.firstaid.extensions.addOnlyNewItems
import com.xhonaldo.firstaid.extensions.containsAny
import com.xhonaldo.firstaid.extensions.getOrThrow
import com.xhonaldo.firstaid.extensions.ifNotEmpty
import com.xhonaldo.firstaid.extensions.notEmpty
import com.xhonaldo.firstaid.extensions.refreshList
import com.xhonaldo.firstaid.extensions.toBase64
import com.xhonaldo.firstaid.extensions.toFormattedString
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import org.junit.Test

class MiscUnitTest {
    @Test
    fun testIfNotEmpty() {
        var executed = false
        listOf("a", "b", "c").ifNotEmpty { executed = true }
        assertTrue(executed)

        executed = false
        emptyList<String>().ifNotEmpty { executed = true }
        assertFalse(executed)
    }

    @Test
    fun testToBase64() {
        assertEquals("VGVzdCBTdHJpbmc=", "Test String".encodeToByteArray().toBase64())
    }

    @Test
    fun testToByteArray() {
        // For testing toByteArray(), instrumentation tests may be needed
    }

    @Test
    fun testNotEmpty() {
        assertTrue(listOf(1, 2, 3).notEmpty())
        assertFalse(emptyList<Int>().notEmpty())
       // assertFalse(null.notEmpty())
    }

    @Test
    fun testGetOrThrow() {
        val map = mapOf("key1" to "value1", "key2" to "value2")
        assertEquals("value1", map.getOrThrow("key1"))
        assertEquals("value2", map.getOrThrow("key2"))

        try {
            map.getOrThrow("key3")
            fail("Expected NoSuchElementException")
        } catch (e: NoSuchElementException) {
            // Test passed
        }
    }

    @Test
    fun testToFormattedString() {
        assertEquals("1,234", 1234.toFormattedString())
        assertEquals("1,234,567", 1234567L.toFormattedString())
    }

    @Test
    fun testRefreshList() {
        val list = ArrayList<Int>()
        list.addAll(listOf(1, 2, 3))

        val newList = list.refreshList(listOf(4, 5, 6))

        assertTrue(newList.containsAll(listOf(4, 5, 6)))
        assertFalse(newList.containsAny(listOf(1, 2, 3)))
    }

    @Test
    fun testAddOnlyNewItems() {
        val list = ArrayList<Int>()
        list.addAll(listOf(1, 2, 3))

        val newList = list.addOnlyNewItems(listOf(2, 3, 4))

        assertTrue(newList.containsAll(listOf(1, 2, 3, 4)))
        assertFalse(newList.containsAny(listOf(2, 3)))
    }

    @Test
    fun testContainsAnyForCollection() {
        val list = listOf(1, 2, 3, 4, 5)

        assertTrue(list.containsAny(2, 5))
        assertTrue(list.containsAny(4, 6))
        assertFalse(list.containsAny(6, 7, 8))
    }

    @Test
    fun testContainsAnyForArray() {
        val array = arrayOf(1, 2, 3, 4, 5)
        assertTrue(array.containsAny(2, 5))
        assertTrue(array.containsAny(4, 6))
        assertFalse(array.containsAny(6, 7, 8))
    }
}