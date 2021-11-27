package com.zinoview.tzfilmsapp.core


/**
 * Test for [com.zinoview.tzfilmsapp.core.Abstract.FilmsMapper]
 */

import org.junit.Assert.*
import org.junit.Test

class FilmsMapperTest {

    @Test
    fun test_success_map_data_to_domain_films() {
        val mapperDataToDomainFilms = TestDataToDomainFilmsMapper()
        val testBaseUsers = listOf(
            TestBaseFilm(
                2, "Fast furios", "fast_furios.jpg"
            ),
            TestBaseFilm(
                4, "Bounce", "bounce.jpg"
            )
        )
        val dataFilms = TestDataFilms.Success(
            testBaseUsers
        )
        val expected = TestDomainFilms.Success(
            listOf(
                TestBaseFilm(
                    2, "Fast furios", "fast_furios.jpg"
                ),
                TestBaseFilm(
                    4, "Bounce", "bounce.jpg"
                )
            )
        )
        val domainFilm = dataFilms.map(mapperDataToDomainFilms)
        assertEquals(expected,domainFilm)
        assertTrue(domainFilm is TestDomainFilms.Success)
    }

    @Test
    fun test_failure_map_data_to_domain_films() {
        val mapperDataToDomainFilms = TestDataToDomainFilmsMapper()
        val dataFilms = TestDataFilms.Failure("No connection")
        val expected = TestDomainFilms.Failure("No connection")
        val domainFilm = dataFilms.map(mapperDataToDomainFilms)
        assertEquals(expected,domainFilm)
        assertTrue(domainFilm is TestDomainFilms.Failure)
    }

    private sealed class TestDataFilms : Abstract.Films {

        data class Success(
            private val films: List<BaseFilm>
        ) : TestDataFilms() {

            override fun <T> map(mapper: Abstract.FilmsMapper<T>): T
                    = mapper.map(films)
        }

        data class Failure(
            private val message: String
        ) : TestDataFilms() {

            override fun <T> map(mapper: Abstract.FilmsMapper<T>): T
                = mapper.map(message)
        }
    }

    private sealed class TestDomainFilms : Abstract.Films {

        data class Success(
            private val films: List<BaseFilm>
        ) : TestDomainFilms() {

            override fun <T> map(mapper: Abstract.FilmsMapper<T>): T
                    = mapper.map(films)
        }

        data class Failure(
            private val message: String
        ) : TestDomainFilms() {

            override fun <T> map(mapper: Abstract.FilmsMapper<T>): T
                    = mapper.map(message)
        }
    }

    private class TestDataToDomainFilmsMapper : Abstract.FilmsMapper<TestDomainFilms> {

        override fun map(films: List<BaseFilm>): TestDomainFilms
            = TestDomainFilms.Success(films)

        override fun map(message: String): TestDomainFilms
            = TestDomainFilms.Failure(message)
    }

    private data class TestBaseFilm(
        private val id: Int,
        private val name: String,
        private val imageUrl: String
    ) : BaseFilm {

        override fun <T> map(mapper: Abstract.FilmMapper<T>): T
            = mapper.map(id,name = name,imageUrl =  imageUrl,localizedName = "",year = -1,rating = 4.0f,description = "",genres = emptyList())
    }
}