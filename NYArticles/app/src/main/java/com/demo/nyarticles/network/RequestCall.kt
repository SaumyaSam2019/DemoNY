package com.demo.nyarticles.network

import android.content.Context
import com.demo.nyarticles.R
import com.demo.nyarticles.fragments.ArticleListFragment
import com.demo.nyarticles.models.ArticlesModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RequestCall(var context:Context) {
    lateinit var myApiInterface: MyApiInterface
    var isDebug = true
    var baseUrl = "https://api.nytimes.com"

    init {
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(2000, TimeUnit.SECONDS)
        builder.connectTimeout(1000, TimeUnit.SECONDS)
        isDebug = true
        if (isDebug) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }

        val client = builder.build()
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                //  .addConverterFactory(SimpleXmlConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        myApiInterface = retrofit.create(MyApiInterface::class.java!!)
    }

    /*fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }*/

    fun fetchPopularArticles(listener: ArticleListFragment.ArticleFetchListener){
        var fetchPopularResponse = myApiInterface.fetchPopularArticles(context.getString(R.string.apiKey))
        fetchPopularResponse.enqueue(object : Callback<ArticlesModel>{
            override fun onFailure(call: Call<ArticlesModel>, t: Throwable) {
                listener.onFetchFailure(t.message!!)
            }

            override fun onResponse(call: Call<ArticlesModel>, response: Response<ArticlesModel>) {
                listener.onFetchSuccess(response.body()!!)
            }

        })
    }

}