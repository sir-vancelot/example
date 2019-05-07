package com.batman.example.presentation

import com.batman.batdroid.presentation.BatmanApplication
import com.batman.example.di.ApplicationComponent
import com.batman.example.di.ApplicationModule
import com.batman.example.di.DaggerApplicationComponent

class ExampleApplication: BatmanApplication() {
    /*
       Allow other classes to inject the same component
    */
    lateinit var applicationComponent: ApplicationComponent

    override val application = "Example"

    override fun di(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }
}