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
     * Inject LogService method
     */
    fun inject(service: LogService)

    companion object {

        /**
         * Init ServiceComponent function
         */
        fun init() = ClerkApp.get().component()
            .plusServiceComponent(LogModule())
    }

}