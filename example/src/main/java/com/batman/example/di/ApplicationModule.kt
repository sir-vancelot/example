package com.batman.example.di

import com.batman.example.domain.db.ExampleDBOpenHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ExampleApplication) {
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
}