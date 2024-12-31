package com.unicoop.nyarticle.ui.home

import com.unicoop.domain.article.model.Article

data class HomeState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null
)
