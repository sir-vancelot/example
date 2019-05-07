package com.batman.batdroid.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Used to obtain references to different Android threads.
 */
object BatmanSchedulerProvider {
    /**
     * Used to obtain a reference to a new thread.
     */
    val newThread: Scheduler get() = Schedulers.newThread()

    /**
     * Used to obtain a reference to the ui thread.
     */
    val uiThread: Scheduler get() = AndroidSchedulers.mainThread()

    /**
     * Used to obtain a reference to the io thread.
     */
    val ioThread: Scheduler get() = Schedulers.io()
}
