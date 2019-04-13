package com.jintin.clerk.app.dagger.module

import com.jintin.clerk.app.dagger.component.LogScope
import com.jintin.clerk.app.obj.ClerkLogDao
import com.jintin.clerk.app.repository.LogRepository
import com.jintin.clerk.app.repository.LogRepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * LogModule for LogRepository
 */
@Module
class LogModule {

    /**
     * Provide LogRepository
     */
    @LogScope
    @Provides
    fun provideLogRepository(dao: ClerkLogDao): LogRepository {
        return LogRepositoryImpl(dao)
    }
}