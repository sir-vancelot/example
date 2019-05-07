package com.batman.example.di

import com.batman.batdroid.di.BatmanActivityScope
import com.batman.example.presentation.example.ExampleActivity
import com.batman.example.presentation.example.example1.Example1View
import com.batman.example.presentation.example.example2.Example2View
import dagger.Component

@Component(dependencies = [ApplicationComponent::class], modules = [ExampleModule::class])
@BatmanActivityScope
interface ExampleComponent {
    // <editor-fold desc="Classes allowed to inject this component">
    fun inject(exampleActivity: ExampleActivity)
    fun inject(example1View: Example1View)
    fun inject(example2View: Example2View)
    // </editor-fold>
}