package com.unicoop.nyarticle.di.module

import android.content.Context
import android.util.Log
import com.unicoop.core.NetworkMonitor
import com.unicoop.core.ConnectivityManagerNetworkMonitor
import com.unicoop.core.Constants
import com.unicoop.data.network.NetworkResponseAdapterFactory
import com.unicoop.nyarticle.BuildConfig

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor =
        ConnectivityManagerNetworkMonitor(context)

    @Singleton
    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        okHttpInterceptor: HttpLoggingInterceptor
    ): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Timber.tag(OkHttpClient::class.java.simpleName).d(message);
    }


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


}