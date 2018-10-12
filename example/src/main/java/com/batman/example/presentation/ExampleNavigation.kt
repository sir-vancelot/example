package com.batman.example.presentation

import com.batman.example.presentation.example2.Example2View
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler

class ExampleNavigation(private val uiRouter: Router) {
    fun showExampleView1() {
        uiRouter.popToRoot()

    }
    fun showExampleView2() {
        uiRouter.popToRoot()
        uiRouter.pushController(RouterTransaction.with(Example2View())
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))

    }
}