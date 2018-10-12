package com.batman.example.di

import android.app.Activity
import com.batman.example.domain.db.ExampleDBOpenHelper
import com.batman.example.domain.db.datastore.Example1Datastore
import com.bluelinelabs.conductor.Router
import com.batman.example.presentation.ExampleNavigation
import com.batman.example.presentation.example1.Example1ViewModel
import com.batman.example.presentation.example2.Example2ViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ExampleModule(private val uiRouter: Router,
                    private val activity: Activity){

    @Provides
    @ActivityScope
    fun getActivity(): Activity{
        return activity
    }

    @Provides
    @ActivityScope
    fun exampleNavigation(): ExampleNavigation {
        return ExampleNavigation(uiRouter)
    }

    @Provides
    @ActivityScope
    fun example1ViewModel(exampleNavigation: ExampleNavigation): Example1ViewModel {
        return Example1ViewModel(exampleNavigation)
    }

    @Provides
    @ActivityScope
    fun example2ViewModel(exampleNavigation: ExampleNavigation): Example2ViewModel {
        return Example2ViewModel(exampleNavigation)
    }

    @Provides
    @ActivityScope
    fun example1DataStore(exampleDBOpenHelper: ExampleDBOpenHelper): Example1Datastore {
        return Example1Datastore(exampleDBOpenHelper)
    }

}