package com.unicoop.domain.article.usecase

import com.unicoop.domain.article.repo.ArticleRepository

class GetArticlesUseCase(
    private val articleRepository: ArticleRepository
) {
    operator fun invoke(period: Int) = articleRepository.getArticles(period)

}