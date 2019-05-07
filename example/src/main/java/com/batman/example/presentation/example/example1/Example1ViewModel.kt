package com.batman.example.presentation.example.example1

import com.batman.batdroid.domain.notification.BatmanNotificationPublisher
import com.batman.batdroid.presentation.activity.view.BatmanViewModel
import com.batman.example.domain.notification.CounterNotification
import com.batman.example.presentation.example.ExampleNavigation
import io.reactivex.disposables.Disposable

class Example1ViewModel(exampleNavigation: ExampleNavigation): BatmanViewModel<Example1View>(exampleNavigation) {

    private var disposable: Disposable? = null

    override fun onViewCreated(view: Example1View) {
        super.onViewCreated(view)
        disposable = BatmanNotificationPublisher.onNotificationPublished
                .filter{ it is CounterNotification }
                .map{ it as CounterNotification }
                .subscribe{view.onData(it.data)}
    }

    override fun onViewDestroyed() {
        super.onViewDestroyed()
        if(disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
    }
}