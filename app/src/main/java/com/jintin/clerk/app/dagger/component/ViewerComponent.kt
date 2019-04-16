package com.jintin.clerk.app.dagger.component

import com.jintin.clerk.app.ClerkApp
import com.jintin.clerk.app.InstantService
import com.jintin.clerk.app.dagger.module.ViewerModule
import com.jintin.clerk.app.ui.LogListFragment
import dagger.Subcomponent

/**
 * ViewerComponent used by dagger
 */
@LogScope
@Subcomponent(modules = [ViewerModule::class])
interface ViewerComponent {

    /**
     * dagger inject method
     */
    fun inject(fragment: LogListFragment)

    /**
     * dagger inject method
     */
    fun inject(service: InstantService)

    companion object {

        /**
         * Init ViewerComponent function
         */
        fun init(): ViewerComponent {
            return ClerkApp.get().component()
                .plusViewerComponent(ViewerModule())
        }

    }

}