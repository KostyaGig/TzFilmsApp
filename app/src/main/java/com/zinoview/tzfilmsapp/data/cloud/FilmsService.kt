package com.zinoview.tzfilmsapp.data.cloud

import retrofit2.http.GET

/**
 * Base url - https://s3-eu-west-1.amazonaws.com/sequeniatesttask/
 *  Response :
 *
 *  {"id": 326,
 *  "localized_name": "Побег из Шоушенка",
 *  "name": "The Shawshank Redemption",
 *  "year": 1994б
 *  "rating": 9.196,
 *  "image_url": "https://st.kp.yandex.net/images/film_iphone/iphone360_326.jpg",
 *  "description": "Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.",
 *  "genres": ["драма"]
 *
}
 */

interface FilmsService {

    @GET("films.json")
    suspend fun films() : CloudFilms.Base
}