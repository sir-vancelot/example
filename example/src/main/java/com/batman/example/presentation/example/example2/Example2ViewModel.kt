package com.batman.example.presentation.example.example2

import com.batman.batdroid.presentation.activity.view.BatmanViewModel
import com.batman.example.domain.command.UpdateExampleStringCommand
import com.batman.example.domain.query.ReadExampleStringQuery
import com.batman.example.presentation.example.ExampleNavigation
import io.reactivex.disposables.Disposable

class Example2ViewModel(exampleNavigation: ExampleNavigation,
                        private val commands: Example2Commands,
                        private val queries: Example2Queries): BatmanViewModel<Example2View>(exampleNavigation) {

    private var readExampleStringDisposable: Disposable? = null

    override fun onViewCreated(view: Example2View) {
        super.onViewCreated(view)
        populateView()
    }

    override fun onViewDestroyed() {
        super.onViewDestroyed()
        if (readExampleStringDisposable != null && !readExampleStringDisposable!!.isDisposed) {
            readExampleStringDisposable!!.dispose()
        }
    }

    override fun populateView() {
        readExampleStringDisposable = queries.readExampleStringQueryHandler.queryList(ReadExampleStringQuery()).doOnSuccess{
            if (it.isNotEmpty()) {
                view!!.onData(it!![0])
            }
        }.subscribe()
    }

    fun updateDatastore() {
        if (view != null && view!!.model != null) {
            commands.updateExampleStringCommandHandler.execute(UpdateExampleStringCommand(view!!.model!!)).subscribe()
        }
    }
}