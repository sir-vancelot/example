package com.batman.example.di

import com.batman.example.infrastructure.datastore.db.ExampleDBOpenHelper
import com.batman.example.presentation.ExampleApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ExampleApplication) {
    /*
       Classes that should be readily available while the modules corresponding application is alive
       should be provided with the @Singleton scope. Classes that do not hold data like commandHandlers
       and queryHandlers should not be. Instead, they should be provided with @Reusable which marks
       them for garbage collection.
    */

    // <editor-fold desc="Provide these classes while the application is alive">
    @Provides
    @Singleton
    fun getApplication(): ExampleApplication {
        return application
    }

    @Provides
    @Singleton
    fun exampleDBOpenHelper(): ExampleDBOpenHelper {
        return ExampleDBOpenHelper(application)
    }
    // </editor-fold>
}