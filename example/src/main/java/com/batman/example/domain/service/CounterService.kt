package com.batman.example.domain.service

import com.batman.example.domain.notification.CounterNotification
import com.batman.example.domain.notification.NotificationPublisher.publishNotification
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class CounterService: LocalService {
    var disposable: Disposable? = null
    var seconds: Long = 0

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

    override fun stop() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
        seconds = 0
    }
}