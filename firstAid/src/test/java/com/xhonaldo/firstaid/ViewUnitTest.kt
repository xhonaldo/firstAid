package com.xhonaldo.firstaid

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.xhonaldo.firstaid.extensions.autoScroll
import com.xhonaldo.firstaid.extensions.gone
import com.xhonaldo.firstaid.extensions.hide
import com.xhonaldo.firstaid.extensions.hideKeyboard
import com.xhonaldo.firstaid.extensions.isEmpty
import com.xhonaldo.firstaid.extensions.isGone
import com.xhonaldo.firstaid.extensions.isInvisible
import com.xhonaldo.firstaid.extensions.isVisible
import com.xhonaldo.firstaid.extensions.show
import com.xhonaldo.firstaid.extensions.takeScreenshot
import com.xhonaldo.firstaid.extensions.value
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ViewUnitTest {
    private lateinit var context: Context
    private lateinit var view: View
    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        view = View(context)
        editText = EditText(context)
        recyclerView = RecyclerView(context)
    }

    @Test
    fun testViewVisibility() {
        view.show()
        assertEquals(View.VISIBLE, view.visibility)

        view.hide()
        assertEquals(View.INVISIBLE, view.visibility)

        view.gone()
        assertEquals(View.GONE, view.visibility)

        assertTrue(view.isVisible())
        assertFalse(view.isGone())
        assertFalse(view.isInvisible())
    }

    @Test
    fun testEditTextExtensions() {
        editText.setText("Test")
        assertFalse(editText.isEmpty())

        val emptyEditText = EditText(context)
        assertTrue(emptyEditText.isEmpty())

        assertEquals("Test", editText.value)
    }

    @Test
    fun testRecyclerViewAutoScroll() {
        // Assuming the RecyclerView has a layout manager
        recyclerView.layoutManager = mock(RecyclerView.LayoutManager::class.java)

        // Assuming a list of items is provided
        val itemList = listOf("Item 1", "Item 2", "Item 3")

        // Auto-scrolling with a delay of 100 milliseconds and scrolling by 1 item
        recyclerView.autoScroll(delayMillis = 100, scrollBy = 1, list = itemList)

        // Ideally, you would assert some conditions here to verify the behavior
    }

    @Test
    fun testDrawableToBitmap() {
//        // Assuming there's a drawable resource named "test_drawable"
//        val drawable = ContextCompat.getDrawable(context, R.drawable.test_drawable)
//        assertNotNull(drawable)
//
//        val bitmap = drawable?.toBitmap()
//        assertNotNull(bitmap)
//        assertTrue(bitmap is Bitmap)
//
//        // Assuming the drawable is a BitmapDrawable
//        if (drawable is BitmapDrawable) {
//            assertEquals(drawable.bitmap, bitmap)
//        }
    }

    @Test
    fun testViewExtensions() {
//        val snackbarMessage = R.string.snackbar_message
//        val snackbarLength = Snackbar.LENGTH_SHORT
//
//        view.showSnackbar(snackbarMessage, snackbarLength) {
//            action(R.string.snackbar_action) { }
//        }

        // Ideally, you would verify the snackbar is shown and has the correct message/action
    }

    @Test
    fun testHideKeyboard() {
        // Mocking inputMethodManager
//        val inputMethodManager = mock(InputMethodManager::class.java)
//        view.hideKeyboard(inputMethodManager)
//        verify(inputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
    }

    @Test
    fun testGetString() {
//        val resourceId = R.string.test_string
//        val expectedString = "Test String"
//        assertEquals(expectedString, view.getString(resourceId))
    }

    @Test
    fun testTakeScreenshot() {
        // Assuming some height and width for the screenshot
        val height = 100
        val width = 100
        val bitmap = view.takeScreenshot(height, width)

        // Ideally, you would verify the generated bitmap
        assertNotNull(bitmap)
        assertEquals(height, bitmap.height)
        assertEquals(width, bitmap.width)
    }
}