package com.zinoview.tzfilmsapp.data

/**
 * Test for [com.zinoview.tzfilmsapp.data.FilmsRepository.Test]
 */

import com.zinoview.tzfilmsapp.core.BaseFilm
import com.zinoview.tzfilmsapp.data.cloud.CloudDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class FilmsRepositoryTest {

    private lateinit var repository: FilmsRepository<DataFilms>

    @Before
    fun setUp() {
        repository = FilmsRepository.Test(
            CloudDataSource.Test()
        )
    }

    @Test
    fun test_success_fetching_data() = runBlocking {
        val expected = DataFilms.Success(
            listOf(
                BaseFilm.Test(
                    1,"TMNT","tmnt.jpg"
                ),
                BaseFilm.Test(
                    2,"1 + 1","1_plus_1.jpg"
                ),
                BaseFilm.Test(
                    3,"Social network","social_network.jpg"
                )
            )
        )
        val actual = repository.films()

        assertEquals(expected, actual)
        assertTrue(actual is DataFilms.Success)
    }

    @Test
    fun test_failure_fetching_data() = runBlocking {
        val expected = DataFilms.Failure("No connection")
        repository.films()
        val actual = repository.films()

        assertEquals(expected, actual)
        assertTrue(actual is DataFilms.Failure)
    }

}