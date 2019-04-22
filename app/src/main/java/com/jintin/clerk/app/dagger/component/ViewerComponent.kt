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
     * Inject LogListFragment method
     */
    fun inject(fragment: LogListFragment)

    /**
     * Inject InstantService method
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