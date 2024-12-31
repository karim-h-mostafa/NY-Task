package com.unicoop.data.article.repo

import com.unicoop.core.Result
import com.unicoop.data.article.dto.ArticlesResponse
import com.unicoop.data.article.service.ArticleAPI
import com.unicoop.data.common.BaseResponse
import com.unicoop.data.network.NetworkResponse
import com.unicoop.domain.article.repo.ArticleRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ArticleRepositoryImpl(
    private val articleAPI: ArticleAPI
) : ArticleRepository {
    override fun getArticles(period: Int) = flow {
        emit(Result.Loading)
        val response = articleAPI.getArticles(period)
        when (response) {
            is NetworkResponse.ApiError<BaseResponse> -> {
                emit(Result.Error(Exception(response.body.status)))
            }

            is NetworkResponse.NetworkError -> {
                emit(Result.Error(Exception("Network Error")))
            }

            is NetworkResponse.Success<ArticlesResponse> -> emit(
                Result.Success(response.body.data?.map {
                    it.toArticle()
                } ?: emptyList())
            )

            is NetworkResponse.UnknownError -> throw response.error ?: Throwable("Unknown Error")
        }
    }.catch {
        emit(Result.Error(Exception(it)))
    }
}