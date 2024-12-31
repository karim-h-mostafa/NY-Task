package com.unicoop.domain.article.repo

import com.unicoop.core.Result
import com.unicoop.domain.article.model.Article
import kotlinx.coroutines.flow.Flow


interface ArticleRepository {
     fun getArticles(period: Int): Flow<Result<List<Article>>>
}