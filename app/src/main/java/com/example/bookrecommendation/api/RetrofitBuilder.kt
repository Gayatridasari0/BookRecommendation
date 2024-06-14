package com.notification.odds.retrofitrelated

import com.example.bookrecommendation.data.Books
import com.example.bookrecommendation.data.RecommendationList
import com.example.bookrecommendation.data.RecommendationReq
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


object RetrofitBuilder {
    private const val BASE_URL = "https://gayidgd.pythonanywhere.com/"

    private fun getRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY // Customize as per your needs

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(loggingInterceptor)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    val apiService: MyApiInterface = getRetrofit().create(MyApiInterface::class.java)
}

interface MyApiInterface {

    @GET("home")
    suspend fun getBooks(): Response<Books?>

    @POST("recommend")
    suspend fun getRecommendations(@Body reqBody: RecommendationReq): Response<RecommendationList?>




}
