package com.unicoop.nyarticle.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {
    @Singleton
    @Provides
    fun provideTimber(): Timber.Tree = Timber.DebugTree()
}