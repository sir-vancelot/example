package com.batman.batdroid.domain.event

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * A static class containing a [RxJava](https://github.com/ReactiveX/RxJava) [Observable] which functions
 * as a thread that can be used to publish [BatmanEvents][BatmanEvent] off of the main thread.
 */
object BatmanEventPublisher {
    /**
     * Provides a PublishSubject that will be used to publish events.
     */
    private val eventSubject = PublishSubject.create<BatmanEvent>()

    /**
     * An [Observable] which when [subscribed][Observable.subscribe] to will receive any events
     * [published][publishEvent].
     *
     * Ex:
     * ```
     * onEventPublished
     *      .filter(it is MyEvent)
     *      .map(it as MyEvent)
     *      .subscribe{
     *          // Do something in response to receiving MyEvent
     *      }
     * ```
     */
    val onEventPublished: Observable<BatmanEvent> = eventSubject

    /**
     * Used to publish [events][BatmanEvent] on the event thread.
     */
    fun publishEvent(event: BatmanEvent) {
        eventSubject.onNext(event)
    }
}