package com.batman.example.presentation.misc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Router

abstract class BatmanActivity: AppCompatActivity() {
    /*
       Setup local variables
    */
    open var uiRouter: Router? = null

    // <editor-fold desc="Initialize subscriptions, load data into the view, and setup view listeners">
    abstract fun initialize(savedInstanceState: Bundle?)
    // </editor-fold>

    // <editor-fold desc="Inject dependencies">
    open fun di() {}
    // </editor-fold>
}