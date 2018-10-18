package com.batman.example.infrastructure.notification

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object NotificationPublisher {
    /*
       Define a PublishSubject that will be used to publish notifications
    */
    private val notificationSubject = PublishSubject.create<Notification>()

    /*
       Define an Observable that classes can subscribe to. Whenever a class subscribes to this, it
       should also have a means to dispose of the subscription (SEE Example1View.kt, onDestroyView())
    */
    val onNotificationPublished: Observable<Notification> = notificationSubject

    // <editor-fold desc="Allow classes to publish notifications on the notification subject">
    fun publishNotification(notification: Notification) {
        notificationSubject.onNext(notification)
    }
    // </editor-fold>
}
