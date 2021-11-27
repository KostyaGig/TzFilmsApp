package com.zinoview.tzfilmsapp.data

import com.zinoview.tzfilmsapp.core.ResourceProvider
import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception
import java.net.UnknownHostException

/**
 * Test for [com.zinoview.tzfilmsapp.data.ExceptionMapper.Test]
 */

class ExceptionMapperTest {

    @Test
    fun test_map_exception_to_string() {
        val exceptionMapper = ExceptionMapper.Test(
            ResourceProvider.Test()
        )

        var exception: Exception = UnknownHostException()
        var expected = "No connection"
        var actual = exceptionMapper.map(exception)

        assertEquals(expected, actual)

        exception = HttpException(Response.success(null))
        expected = "Some went wrong"
        actual = exceptionMapper.map(exception)

        assertEquals(expected, actual)
    }

}