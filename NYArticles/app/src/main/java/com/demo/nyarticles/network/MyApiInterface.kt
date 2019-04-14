package com.demo.nyarticles.network

import com.demo.nyarticles.models.ArticlesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MyApiInterface {
    @GET("/svc/mostpopular/v2/mostviewed/all-sections/7.json")
    fun fetchPopularArticles(@Query("api-key") apiKey:String): Call<ArticlesModel>
}