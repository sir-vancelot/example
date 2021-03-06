package com.batman.example.di

import com.batman.batdroid.di.BatmanApplicationScope
import com.batman.example.domain.datastore.db.ExampleDBOpenHelper
import com.batman.example.presentation.ExampleApplication
import dagger.Component

@BatmanApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    // <editor-fold desc="Classes allowed to inject this component">
    fun inject(exampleApplication: ExampleApplication)
    // </editor-fold>

    // <editor-fold desc="Classes provided to child components"
    fun exampleApplication(): ExampleApplication
    fun exampleDBOpenHelper(): ExampleDBOpenHelper
    // </editor-fold>
}