package com.batman.example.infrastructure.service

import com.batman.example.infrastructure.notification.CounterNotification
import com.batman.example.infrastructure.notification.NotificationPublisher.publishNotification
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class CounterService: LocalService {
    var disposable: Disposable? = null
    var seconds: Long = 0

    // <editor-fold desc="Begin the service">
    override fun start() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
            seconds = 0
        }
        disposable = Observable.interval(1, TimeUnit.SECONDS).subscribe {
            seconds += 1
            publishNotification(CounterNotification(seconds))
        }
    }
    // </editor-fold>

    // <editor-fold desc="Stop the service">
    override fun stop() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
        seconds = 0
    }
    // </editor-fold>
}