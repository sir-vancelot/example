package com.batman.example.presentation.example

import com.batman.example.presentation.example.example2.Example2View
import com.batman.example.presentation.misc.BatmanNavigation
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler

class ExampleNavigation(private val uiRouter: Router): BatmanNavigation() {
    override fun navigateTo(tag: String) {
        when (tag) {
            TAGS.EXAMPLE_1_VIEW -> showExample1View()
            TAGS.EXAMPLE_2_VIEW -> showExample2View()
        }
    }

    // <editor-fold desc="Define tags for the different view managed by the navigation">
    object TAGS {
        const val EXAMPLE_1_VIEW = "example_1_view"
        const val EXAMPLE_2_VIEW = "example_2_view"
    }
    // </editor-fold>

    // <editor-fold desc="Show the Example1View">
    private fun showExample1View() {
        uiRouter.popToRoot()
    }
    // </editor-fold>

    // <editor-fold desc="Show the Example2View">
    private fun showExample2View() {
        if (uiRouter.getControllerWithTag(TAGS.EXAMPLE_2_VIEW) == null) {
            uiRouter.pushController(RouterTransaction.with(Example2View())
                    .tag(TAGS.EXAMPLE_2_VIEW)
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler()))
        }
    }
    // </editor-fold>
}