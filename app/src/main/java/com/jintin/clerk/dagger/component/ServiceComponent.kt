package com.jintin.clerk.dagger.component

import com.jintin.clerk.app.ClerkApp
import com.jintin.clerk.app.LogService
import com.jintin.clerk.dagger.module.LogModule
import com.jintin.clerk.obj.ClerkLogDao
import dagger.Subcomponent

@LogScope
@Subcomponent(modules = [LogModule::class])
interface ServiceComponent {

    fun inject(fragment: LogService)

    fun clerkLogDao(): ClerkLogDao

    companion object {
        var component: ServiceComponent? = null

        fun init(): ServiceComponent {
            return component ?: ClerkApp.get().component()
                .plusServiceComponent(LogModule())
                .also {
                    component = it
                }
        }

        fun clear() {
            component = null
        }
    }
}