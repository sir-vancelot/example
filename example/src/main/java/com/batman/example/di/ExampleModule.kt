package com.batman.example.di

import com.batman.batdroid.di.BatmanActivityScope
import com.batman.example.domain.command.UpdateExampleStringCommandHandler
import com.batman.example.domain.datastore.ExampleStringDatastore
import com.batman.example.domain.datastore.db.ExampleDBOpenHelper
import com.batman.example.domain.query.ReadExampleStringQueryHandler
import com.batman.example.domain.service.CounterService
import com.batman.example.presentation.example.ExampleActivity
import com.batman.example.presentation.example.ExampleNavigation
import com.batman.example.presentation.example.example1.Example1ViewModel
import com.batman.example.presentation.example.example2.Example2Commands
import com.batman.example.presentation.example.example2.Example2Queries
import com.batman.example.presentation.example.example2.Example2ViewModel
import com.bluelinelabs.conductor.Router
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class ExampleModule(private val uiRouter: Router,
                    private val activity: ExampleActivity){
    /*
       Classes that should be readily available while the modules corresponding activity is alive
       should be provided with the @BatmanActivityScope. Classes that do not hold data like commandHandlers
       and queryHandlers should not be. Instead, they should be provided with @Reusable which marks
       them for garbage collection.
    */

    // <editor-fold desc="Provide these classes while the activity is alive">
    @Provides
    @BatmanActivityScope
    fun getActivity(): ExampleActivity{
        return activity
    }

    @Provides
    @BatmanActivityScope
    fun exampleNavigation(): ExampleNavigation {
        return ExampleNavigation(uiRouter)
    }

    @Provides
    @BatmanActivityScope
    fun example1ViewModel(exampleNavigation: ExampleNavigation): Example1ViewModel {
        return Example1ViewModel(exampleNavigation)
    }

    @Provides
    @BatmanActivityScope
    fun example2ViewModel(exampleNavigation: ExampleNavigation,
                          example2Commands: Example2Commands,
                          example2Queries: Example2Queries): Example2ViewModel {
        return Example2ViewModel(exampleNavigation, example2Commands, example2Queries)
    }

    @Provides
    @BatmanActivityScope
    fun example1DataStore(exampleDBOpenHelper: ExampleDBOpenHelper): ExampleStringDatastore {
        return ExampleStringDatastore(exampleDBOpenHelper)
    }

    @Provides
    @BatmanActivityScope
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