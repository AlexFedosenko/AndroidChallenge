package com.androidchallenge.di

import com.androidchallenge.api
import com.androidchallenge.repository.MainRepository
import com.androidchallenge.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {

    @AppScope
    @Provides
    fun provideAppCoroutineScope(
        coroutineContext: CoroutineContext,
    ): CoroutineScope {
        return CoroutineScope(coroutineContext)
    }

    /**
     * For 'heavy' coroutine networking this thread pool might be expanded.
     * SupervisorJob() could be added to the scope as well
     */
    @AppScope
    @Provides
    fun provideCoroutineContext(): CoroutineContext {
        return newFixedThreadPoolContext(1,"Thread Pool")
    }

    /**
     * Just pass already created and existing `api` instance, no need in creating another one
     */
    @AppScope
    @Provides
    fun provideMainRepository(): MainRepository {
        return MainRepositoryImpl(
            backendService = api
        )
    }
}