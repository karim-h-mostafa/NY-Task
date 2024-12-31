package com.unicoop.nyarticle.di.module

import com.unicoop.data.article.repo.ArticleRepositoryImpl
import com.unicoop.data.article.service.ArticleAPI
import com.unicoop.domain.article.repo.ArticleRepository
import com.unicoop.domain.article.usecase.GetArticlesUseCase
import com.unicoop.domain.article.usecase.GetCombinedArticles
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArticleModule {
    @Singleton
    @Provides
    fun provideArticle(retrofit: Retrofit): ArticleAPI = retrofit.create(ArticleAPI::class.java)

    @Singleton
    @Provides
    fun provideArticleRepository(articleAPI: ArticleAPI): ArticleRepository = ArticleRepositoryImpl(articleAPI)


    @Provides
    fun provideGetArticlesUseCase(articleRepository: ArticleRepository): GetArticlesUseCase = GetArticlesUseCase(articleRepository)

    @Provides
    fun provideGetCombinedArticles(): GetCombinedArticles = GetCombinedArticles()



}