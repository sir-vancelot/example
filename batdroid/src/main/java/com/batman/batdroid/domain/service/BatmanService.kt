package com.batman.batdroid.domain.service

import io.reactivex.disposables.CompositeDisposable

/**
 * A [BatmanService] is defined to be an ongoing or repeating action that can be [started][start],
 * [stopped][stop], and [queried][isRunning] to determine if the service is active.
 */
abstract class BatmanService {
    /**
     * Used to keep track of any [disposables][CompositeDisposable] so that they can be properly
     * [disposed][CompositeDisposable.dispose] of when the service is stopped.
     */
    protected var disposables: CompositeDisposable? = null

    /**
     * Used to start the [BatmanService]. Children BatmanServices should reference super.start() to
     * prevent multiple instances of their service from running at the same time and to properly initialize
     * start setup.
     * Ex:
     * ```
     * override fun start() {
     *      super.start()
     *      Observable.timer(5, TimeUnit.SECONDS).subscribe {
     *          // Do something every 5s
     *      }.addTo(disposables!!)
     *      Observable.timer(1, TimeUnit.MINUTES).subscribe {
     *          // Do something every 1min
     *      }.addTo(disposables!!)
     * }
     * ```
     */
    open fun start() {
        stop()
        disposables = CompositeDisposable()
    }

    /**
     * Used to stop the [BatmanService].
     */
    open fun stop() {
        disposables?.dispose()
        disposables = null
    }

    /**
     * Used to determine if the [BatmanService] is active.
     */
    fun isRunning(): Boolean {
        return (disposables != null && !disposables!!.isDisposed)
    }
}