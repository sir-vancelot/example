package com.batman.example.di

import android.app.Activity
import com.batman.example.domain.db.ExampleDBOpenHelper
import com.batman.example.domain.datastore.ExampleStringDatastore
import com.batman.example.infrastructure.command.UpdateExampleStringCommandHandler
import com.batman.example.infrastructure.query.ReadExampleStringQueryHandler
import com.batman.example.infrastructure.service.CounterService
import com.batman.example.presentation.example.ExampleActivity
import com.bluelinelabs.conductor.Router
import com.batman.example.presentation.example.ExampleNavigation
import com.batman.example.presentation.example.example1.Example1ViewModel
import com.batman.example.presentation.example.example2.Example2Commands
import com.batman.example.presentation.example.example2.Example2Queries
import com.batman.example.presentation.example.example2.Example2ViewModel
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class ExampleModule(private val uiRouter: Router,
                    private val activity: ExampleActivity){
    /*
       Classes that should be readily available while the modules corresponding activity is alive
       should be provided with the @ActivityScope. Classes that do not hold data like commandHandlers
       and queryHandlers should not be. Instead, they should be provided with @Reusable which marks
       them for garbage collection.
    */

    // <editor-fold desc="Provide these classes while the activity is alive">
    @Provides
    @ActivityScope
    fun getActivity(): ExampleActivity{
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
    fun example2ViewModel(exampleNavigation: ExampleNavigation,
                          example2Commands: Example2Commands,
                          example2Queries: Example2Queries): Example2ViewModel {
        return Example2ViewModel(exampleNavigation, example2Commands, example2Queries)
    }

    @Provides
    @ActivityScope
    fun example1DataStore(exampleDBOpenHelper: ExampleDBOpenHelper): ExampleStringDatastore {
        return ExampleStringDatastore(exampleDBOpenHelper)
    }

    @Provides
    @ActivityScope
    fun counterService(): CounterService {
        return CounterService()
    }
    // </editor-fold>

    // <editor-fold desc="Provide these as they are needed and the activity is alive">
    @Provides
    @Reusable
    fun readExampleStringQueryHandler(exampleStringDatastore: ExampleStringDatastore): ReadExampleStringQueryHandler {
        return ReadExampleStringQueryHandler(exampleStringDatastore)
    }

    @Provides
    @Reusable
    fun updateExampleStringCommandHandler(exampleStringDatastore: ExampleStringDatastore): UpdateExampleStringCommandHandler {
        return UpdateExampleStringCommandHandler(exampleStringDatastore)
    }

    @Provides
    @Reusable
    fun example2Commands(updateExampleStringCommandHandler: UpdateExampleStringCommandHandler): Example2Commands {
        return Example2Commands(updateExampleStringCommandHandler)
    }

    @Provides
    @Reusable
    fun example2Queries(readExampleStringQueryHandler: ReadExampleStringQueryHandler): Example2Queries {
        return Example2Queries(readExampleStringQueryHandler)
    }
    // </editor-fold>
}