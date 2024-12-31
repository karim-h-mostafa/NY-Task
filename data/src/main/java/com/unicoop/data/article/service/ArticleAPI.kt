package com.unicoop.data.article.service

import com.unicoop.data.BuildConfig
import com.unicoop.data.article.dto.ArticlesResponse
import com.unicoop.data.common.BaseResponse
import com.unicoop.data.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ArticleAPI {
    @GET("all-sections/{period}.json")
    suspend fun getArticles(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String = BuildConfig.API_KEY,
    ): NetworkResponse<ArticlesResponse,BaseResponse>
}