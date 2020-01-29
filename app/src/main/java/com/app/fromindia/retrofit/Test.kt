package com.app.fromindia.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Test {


    val baseURL: String = "https://api.themoviedb.org/3/"
    var retofit: Retrofit? = null

    val client: Retrofit
        get() {

            if (retofit == null) {
                retofit = Retrofit.Builder()
                        .baseUrl(baseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retofit!!
        }

}