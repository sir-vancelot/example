package com.batman.batdroid.domain.notification

import com.batman.batdroid.utils.BatmanSchedulerProvider
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * A static class containing a [RxJava](https://github.com/ReactiveX/RxJava) [Observable] which functions
 * as a thread that can be used to publish [BatmanNotifications][BatmanNotification] on the main thread.
 */
object BatmanNotificationPublisher {
    /**
     * Provides a PublishSubject that will be used to publish notifications.
     */
    private val notificationSubject = PublishSubject.create<BatmanNotification>()

    /**
     * An [Observable] which when [subscribed][Observable.subscribe] to will receive any events
     * [published][publishNotification] on the main thread.
     *
     * Ex:
     * ```
     * onNotificationPublished
     *      .filter(it is MyNotification)
     *      .map(it as MyNotification)
     *      .subscribe{
     *          // Do something in response to receiving MyNotification
     *      }
     * ```
     */
    val onNotificationPublished: Observable<BatmanNotification> = notificationSubject
                                                                        .observeOn(BatmanSchedulerProvider.uiThread)
                                                                        .subscribeOn(BatmanSchedulerProvider.uiThread)

    /**
     * Used to publish [notifications][BatmanNotification] on the main thread.
     */
    fun publishNotification(notification: BatmanNotification) {
        notificationSubject.onNext(notification)
    }
}
