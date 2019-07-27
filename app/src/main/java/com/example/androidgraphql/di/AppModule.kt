package com.example.androidgraphql.di

import com.apollographql.apollo.ApolloClient
import com.example.androidgraphql.AppBus
import com.example.androidgraphql.Bus
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT = 2
private const val GRAPHQL_API = "https://api.graphql.jobs"

@Module
class AppModule {

    @Provides
    @Reusable
    internal fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.MINUTES)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.MINUTES)
        return builder.build()
    }

    @Provides
    @Reusable
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(GRAPHQL_API)
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAppBus(): Bus {
        return AppBus(EventBus.getDefault())
    }
}