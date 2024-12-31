package com.unicoop.domain.article.usecase

import com.unicoop.core.Result
import com.unicoop.core.doIfError
import com.unicoop.core.doIfLoading
import com.unicoop.core.doIfSuccess
import com.unicoop.domain.article.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCombinedArticles(
) {
    operator fun invoke(
        firstCallList: Result<List<Article>>,
        secondCallList: Result<List<Article>>
    ): Flow<Result<List<Article>>> = flow {
        val combinedList = mutableListOf<Article>()
        with(firstCallList) {
            doIfSuccess {
                combinedList.addAll(it)
            }
            doIfError {
                emit(Result.Error(Exception("Error")))
            }
            doIfLoading {
                emit(Result.Loading)
            }

        }
        with(secondCallList) {
            doIfSuccess {
                combinedList.addAll(it)
            }
            doIfError {
                emit(Result.Error(Exception("Error")))
            }
            doIfLoading {
                emit(Result.Loading)
            }

        }
        combinedList.sortByDescending { it.publishedDate }
        emit(Result.Success(combinedList))
    }
}