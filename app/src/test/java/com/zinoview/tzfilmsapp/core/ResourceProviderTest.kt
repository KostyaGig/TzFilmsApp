package com.zinoview.tzfilmsapp.core

/**
 * Test for [com.zinoview.tzfilmsapp.core.ResourceProvider.Test]
 */

import org.junit.Assert.*
import org.junit.Test
import com.zinoview.tzfilmsapp.R

class ResourceProviderTest {

    @Test
    fun test_map_string_id_to_string() {
        val resourceProvider = ResourceProvider.Test()
        var stringId = R.string.no_connection_text
        var expected = "No connection"
        var actual = resourceProvider.string(stringId)

        assertEquals(expected, actual)

        stringId = R.string.some_went_wrong_text
        expected = "Some went wrong"
        actual = resourceProvider.string(stringId)

        assertEquals(expected, actual)
    }

}