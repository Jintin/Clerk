package com.jintin.clerk.app.dagger.component

import com.jintin.clerk.app.ClerkApp
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

    companion object {
        var component: ViewerComponent? = null

        /**
         * Init ViewerComponent function
         */
        fun init(): ViewerComponent {
            return component ?: ClerkApp.get().component()
                .plusViewerComponent(ViewerModule())
                .also {
                    component = it
                }
        }

        /**
         * Clear component
         */
        fun clear() {
            component = null
        }
    }
}