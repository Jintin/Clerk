package com.jintin.clerk.app.dagger.module

import com.jintin.clerk.app.dagger.component.LogScope
import com.jintin.clerk.app.repository.LogRepository
import com.jintin.clerk.app.viewmodel.LogListViewModel
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