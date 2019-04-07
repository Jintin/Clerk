package com.jintin.clerk.app

import android.app.Application
import com.jintin.clerk.app.dagger.component.AppComponent
import com.jintin.clerk.app.dagger.component.DaggerAppComponent
import com.jintin.clerk.app.dagger.module.RoomModule

class ClerkApp : Application() {

    lateinit var component: AppComponent

    companion object {
        lateinit var instance: ClerkApp
        fun get(): ClerkApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder().roomModule(RoomModule(this)).build()
    }

    fun component(): AppComponent {
        return component
    }

}