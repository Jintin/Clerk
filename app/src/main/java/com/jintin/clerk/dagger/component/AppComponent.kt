package com.jintin.clerk.dagger.component

import android.app.Application
import com.jintin.clerk.dagger.module.LogModule
import com.jintin.clerk.dagger.module.RoomModule
import com.jintin.clerk.dagger.module.ViewerModule
import com.jintin.clerk.obj.ClerkLogDao
import com.jintin.clerk.obj.ClerkLogDatabase
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