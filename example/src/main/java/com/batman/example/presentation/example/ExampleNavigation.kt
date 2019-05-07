package com.batman.example.presentation.example

import android.os.Bundle
import com.batman.example.presentation.example.example2.Example2View
import com.batman.batdroid.presentation.activity.BatmanNavigation
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler

class ExampleNavigation(uiRouter: Router): BatmanNavigation(uiRouter) {
    override fun navigateTo(tag: String, bundle: Bundle?) {
        when (tag) {
            TAGS.EXAMPLE_1_VIEW -> showExample1View()
            TAGS.EXAMPLE_2_VIEW -> showExample2View()
        }
    }

    object TAGS: BatmanNavigation.TAGS() {
        const val EXAMPLE_1_VIEW = "example_1_view"
        const val EXAMPLE_2_VIEW = "example_2_view"
    }

    private fun showExample1View() {
        uiRouter?.popToRoot()
    }

    private fun showExample2View() {
        if (uiRouter?.getControllerWithTag(TAGS.EXAMPLE_2_VIEW) == null) {
            uiRouter?.pushController(RouterTransaction.with(Example2View())
                    .tag(TAGS.EXAMPLE_2_VIEW)
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler()))
        }
    }
}