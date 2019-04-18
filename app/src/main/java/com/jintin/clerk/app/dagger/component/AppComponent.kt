package com.jintin.clerk.app.dagger.component

import android.app.Application
import com.jintin.clerk.app.dagger.module.LogModule
import com.jintin.clerk.app.dagger.module.RoomModule
import com.jintin.clerk.app.dagger.module.ViewerModule
import dagger.Component
import javax.inject.Singleton

/**
 * AppComponent used by dagger
 */
@Singleton
@Component(modules = [RoomModule::class])
interface AppComponent {

    /**
     * dagger inject method
     */
    fun inject(application: Application)

    /**
     * ViewerModule binding
     */
    fun plusViewerComponent(module: ViewerModule): ViewerComponent

    /**
     * ServiceModule binding
     */
    fun plusServiceComponent(module: LogModule): ServiceComponent

}