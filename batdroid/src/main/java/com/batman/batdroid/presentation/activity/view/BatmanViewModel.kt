package com.batman.batdroid.presentation.activity.view

import android.os.Bundle
import com.batman.batdroid.presentation.activity.BatmanNavigation
import io.reactivex.disposables.CompositeDisposable

/**
 * Used to provide a [BatmanView] with the means to [navigate][navigateTo] other [views][BatmanView]
 * as well as provide space to listen for domain [notifications][com.batman.batdroid.domain.notification.BatmanNotification]
 * and domain [events][com.batman.batdroid.domain.event.BatmanEvent].
 */
abstract class BatmanViewModel<View>(private val navigation: BatmanNavigation? = null) {

    /**
     * Used to hold a reference to the view so that [view.onData][BatmanView.onData] can be called.
     */
    protected var view: View? = null

    /**
     * Used to hold a reference to a collection of [Disposables][io.reactivex.disposables.Disposable]
     * which will be cleaned up when the view is [destroyed][onViewDestroyed].
     */
    protected var disposables: CompositeDisposable? = null

    /**
     * Used to initialize the [view], the [disposables], and automate a call to [populateView].
     *
     * [onViewCreated] can also be overriden to start any services that the [BatmanViewModel] tracks.
     */
    open fun onViewCreated(view: View) {
        this.view = view
        disposables?.dispose()
        disposables = CompositeDisposable()
        populateView()
    }

    /**
     * Used to release the reference to the [view] and [dispose][CompositeDisposable.dispose].
     */
    open fun onViewDestroyed() {
        this.view = null
        disposables?.dispose()
    }

    /**
     * Used to query any models required to load data into a [view] as well as initialize any [notification][com.batman.batdroid.domain.notification.BatmanNotification]
     * or [event][com.batman.batdroid.domain.event.BatmanEvent] listeners.
     *
     * Ex:
     * ```
     * myQueryHandler.query(MyQuery()).subscribe {
     *      view.onData(it)
     * }
     *
     * onNotificationPublished
     *      .filter(it is MyNotification)
     *      .map(it as MyNotification)
     *      .subscribe {
     *          view.onData(it.myData)
     *      }
     *
     * onEventPublished
     *      .filter(it is MyEvent)
     *      .map(it as MyEvent)
     *      .subscribe {
     *          if (it.isStart) {
     *              myService.start()
     *          } else {
     *              myService.stop()
     *          }
     *      }
     * ```
     */
    open fun populateView() {}

    /**
     * Used to navigate from the current [view] to another via a [BatmanNavigation].
     */
    open fun navigateTo(tag: String, bundle: Bundle? = null) {
        navigation?.navigateTo(tag, bundle)
    }
}