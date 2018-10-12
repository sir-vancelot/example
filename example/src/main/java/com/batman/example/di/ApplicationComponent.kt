package com.batman.example.di

import com.batman.example.domain.db.ExampleDBOpenHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    // Applications, Activities, and Views with this context
    fun inject(exampleApplication: ExampleApplication)

    // This we want provided to dependent components
    //      SEE ExampleComponent's @Component Tag
    fun exampleApplication(): ExampleApplication
    fun exampleDBOpenHelper(): ExampleDBOpenHelper
}