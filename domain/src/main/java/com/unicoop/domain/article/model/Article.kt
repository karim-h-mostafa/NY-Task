package com.unicoop.domain.article.model

data class Article(
    val uri: String,
    val url: String,
    val id: Long,
    val assetId: Long,
    val source: String,
    val publishedDate: String,
    val title: String,
    val body: String
)
