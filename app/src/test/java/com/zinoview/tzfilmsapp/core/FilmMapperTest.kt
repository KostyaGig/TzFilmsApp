package com.zinoview.tzfilmsapp.core

import org.junit.Test

/**
 * Test for [com.zinoview.tzfilmsapp.core.Abstract.FilmMapper]
 */

import org.junit.Assert.*

class FilmMapperTest {

    @Test
    fun test_map_data_to_domain_film() {
        val dataToDomainFilmMapper = TestDataToDomainFilmMapper()
        val dataFilm = TestDataFilm(
            1,"1 + 1","1_plus_1.jpg"
        )
        val domainFilm = dataFilm.map(dataToDomainFilmMapper)
        val expected = TestDomainFilm(
            1,"1 + 1","1_plus_1.jpg"
        )
        assertEquals(expected,domainFilm)
    }

    @Test
    fun test_map_data_to_domain_films() {
        val dataToDomainFilmMapper = TestDataToDomainFilmMapper()
        val dataFilms = listOf(
            TestDataFilm(
                1,"TMNT","tmnt.jpg"
            ),
            TestDataFilm(
                2,"1 + 1","1_plus_1.jpg"
            ),
            TestDataFilm(
                3,"Social network","social_network.jpg"
            )
        )
        val domainFilm = dataFilms.map { dataFilm ->
            dataFilm.map(dataToDomainFilmMapper)
        }
        val expected = listOf(
            TestDomainFilm(
                1,"TMNT","tmnt.jpg"
            ),
            TestDomainFilm(
                2,"1 + 1","1_plus_1.jpg"
            ),
            TestDomainFilm(
                3,"Social network","social_network.jpg"
            )
        )
        assertEquals(expected,domainFilm)
    }

    private data class TestDataFilm(
        private val id: Int,
        private val name: String,
        private val imageUrl: String
    ) : BaseFilm {

        override fun <T> map(mapper: Abstract.FilmMapper<T>): T
            = mapper.map(id,name = name,imageUrl =  imageUrl,localizedName = "",year = -1,rating = 4.0f,description = "",genres = emptyList())
    }

    private data class TestDomainFilm(
        private val id: Int,
        private val name: String,
        private val imageUrl: String
    ) : BaseFilm {

        override fun <T> map(mapper: Abstract.FilmMapper<T>): T
            = mapper.map(id,name = name,imageUrl =  imageUrl,localizedName = "",year = -1,rating = 4.0f,description = "",genres = emptyList())
    }

    private inner class TestDataToDomainFilmMapper : Abstract.FilmMapper<TestDomainFilm> {

        override fun map(
            id: Int,
            localizedName: String,
            name: String,
            year: Int,
            rating: Float,
            imageUrl: String,
            description: String,
            genres: List<String>
        ): TestDomainFilm
            = TestDomainFilm(
                id, name, imageUrl
            )
    }
}