package com.jintin.clerk.app.dagger.component

import com.jintin.clerk.app.ClerkApp
import com.jintin.clerk.app.dagger.module.ViewerModule
import com.jintin.clerk.app.obj.ClerkLogDao
import com.jintin.clerk.app.ui.LogListFragment
import dagger.Subcomponent

@LogScope
@Subcomponent(modules = [ViewerModule::class])
interface ViewerComponent {

    fun inject(fragment: LogListFragment)

    fun clerkLogDao(): ClerkLogDao

    companion object {
        var component: ViewerComponent? = null

        fun init(): ViewerComponent {
            return component ?: ClerkApp.get().component()
                .plusViewerComponent(ViewerModule())
                .also {
                    component = it
                }
        }

        fun clear() {
            component = null
        }
    }
}