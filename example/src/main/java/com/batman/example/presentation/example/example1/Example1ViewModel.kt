package com.batman.example.presentation.example.example1

import com.batman.example.domain.notification.CounterNotification
import com.batman.example.domain.notification.NotificationPublisher
import com.batman.example.presentation.example.ExampleNavigation
import com.batman.example.presentation.misc.BatmanViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class Example1ViewModel(exampleNavigation: ExampleNavigation): BatmanViewModel<Example1View>(exampleNavigation) {

    private var disposable: Disposable? = null

    override fun onViewCreated(view: Example1View) {
        disposable = NotificationPublisher.onNotificationPublished
                .filter{ it is CounterNotification }
                .map{ it as CounterNotification }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe{view.onData(it.data)}
    }

    override fun onViewDestroyed() {
        if(disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
    }
}