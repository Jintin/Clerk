package com.jintin.clerk.dagger.module

import com.jintin.clerk.dagger.component.LogScope
import com.jintin.clerk.obj.ClerkLogDao
import com.jintin.clerk.repository.LogRepository
import com.jintin.clerk.repository.LogRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class LogModule {

    @LogScope
    @Provides
    fun provideLogRepository(dao: ClerkLogDao): LogRepository {
        return LogRepositoryImpl(dao)
    }
}