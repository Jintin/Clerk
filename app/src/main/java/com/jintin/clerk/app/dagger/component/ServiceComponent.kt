package com.jintin.clerk.app.dagger.component

import com.jintin.clerk.app.ClerkApp
import com.jintin.clerk.app.LogService
import com.jintin.clerk.app.dagger.module.LogModule
import dagger.Subcomponent

/**
 * ServiceComponent used by dagger
 */
@LogScope
@Subcomponent(modules = [LogModule::class])
interface ServiceComponent {

    /**
     * dagger inject method
     */
    fun inject(fragment: LogService)

    companion object {
        var component: ServiceComponent? = null

        /**
         * Init ServiceComponent function
         */
        fun init(): ServiceComponent {
            return component ?: ClerkApp.get().component()
                .plusServiceComponent(LogModule())
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