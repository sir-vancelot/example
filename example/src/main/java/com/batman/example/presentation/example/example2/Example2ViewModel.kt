package com.batman.example.presentation.example.example2

import com.batman.example.domain.command.UpdateExampleStringCommand
import com.batman.example.domain.query.ReadExampleStringQuery
import com.batman.example.presentation.example.ExampleNavigation
import com.batman.example.presentation.misc.BatmanViewModel
import io.reactivex.disposables.Disposable

class Example2ViewModel(exampleNavigation: ExampleNavigation,
                        example2Commands: Example2Commands,
                        example2Queries: Example2Queries): BatmanViewModel<Example2View>(exampleNavigation, example2Commands, example2Queries) {

    override var view: Example2View? = null

    private var readExampleStringDisposable: Disposable? = null

    override fun onViewCreated(view: Example2View) {
        this.view = view
        populateView()
    }

    override fun onViewDestroyed() {
        this.view = null

        if (readExampleStringDisposable != null && !readExampleStringDisposable!!.isDisposed) {
            readExampleStringDisposable!!.dispose()
        }
    }

    override fun populateView() {
        readExampleStringDisposable = (queries as Example2Queries).readExampleStringQueryHandler.execute(ReadExampleStringQuery()).subscribe{view!!.onData(it[0])}
    }

    override fun updateDatastore() {
        if (view != null && view!!.model != null) {
            (commands as Example2Commands).updateExampleStringCommandHandler.execute(UpdateExampleStringCommand(view!!.model!!)).subscribe()
        }
    }
}