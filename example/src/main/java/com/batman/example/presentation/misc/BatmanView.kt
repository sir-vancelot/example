package com.batman.example.presentation.misc

import android.view.View
import com.bluelinelabs.conductor.Controller

abstract class BatmanView<Data>: Controller() {
    /*
       Setup local vars
    */
    open var batmanView: View? = null
    open var model: Data? = null

    // <editor-fold desc="Inject dependencies">
    open fun di() {}
    // </editor-fold>

    // <editor-fold desc="Initialize subscriptions, load data into the view, and setup view listeners">
    abstract fun initialize(view: View)
    // </editor-fold>

    // <editor-fold desc="Execute in response to data being provided to the view">
    open fun onData(data: Data) {}
    // </editor-fold>
}