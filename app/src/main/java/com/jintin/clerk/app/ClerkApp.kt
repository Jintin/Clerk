package com.jintin.clerk.app

import android.app.Application
import com.jintin.clerk.app.dagger.component.AppComponent
import com.jintin.clerk.app.dagger.component.DaggerAppComponent
import com.jintin.clerk.app.dagger.module.RoomModule

/**
 * ClerkApp
 */
class ClerkApp : Application() {

    lateinit var component: AppComponent

    companion object {

        lateinit var instance: ClerkApp

        /**
         *Get ClerkApp instance
         */
        fun get(): ClerkApp = instance

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder().roomModule(RoomModule(this)).build()
    }

    /**
     * Get AppComponent instance
     */
    fun component(): AppComponent {
        return component
    }

}