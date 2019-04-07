package com.jintin.clerk.app.dagger.component

import android.app.Application
import com.jintin.clerk.app.dagger.module.LogModule
import com.jintin.clerk.app.dagger.module.RoomModule
import com.jintin.clerk.app.dagger.module.ViewerModule
import com.jintin.clerk.app.obj.ClerkLogDao
import com.jintin.clerk.app.obj.ClerkLogDatabase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])
interface AppComponent {

    fun inject(application: Application)

    fun clerkLogDatabase(): ClerkLogDatabase

    fun clerkLogDao(): ClerkLogDao

    fun plusViewerComponent(module: ViewerModule): ViewerComponent

    fun plusServiceComponent(module: LogModule): ServiceComponent
}