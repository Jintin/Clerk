package com.jintin.clerk.dagger.module

import com.jintin.clerk.dagger.component.LogScope
import com.jintin.clerk.repository.LogRepository
import com.jintin.clerk.viewmodel.LogListViewModel
import dagger.Module
import dagger.Provides

@Module(includes = [LogModule::class])
class ViewerModule {

    @LogScope
    @Provides
    fun provideViewModelFactory(logRepository: LogRepository): LogListViewModel.Factory {
        return LogListViewModel.Factory(logRepository)
    }
}