package com.batman.example.di

import com.batman.example.presentation.ExampleActivity
import com.batman.example.presentation.example1.Example1View
import com.batman.example.presentation.example2.Example2View
import dagger.Component
import javax.inject.Singleton

@Component(dependencies = [ApplicationComponent::class], modules = [ExampleModule::class])
@ActivityScope
interface ExampleComponent {
    fun inject(exampleActivity: ExampleActivity)
    fun inject(example1View: Example1View)
    fun inject(example2View: Example2View)
}