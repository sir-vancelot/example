package com.batman.example.presentation.example.example1

import android.view.View
import com.batman.batdroid.presentation.activity.view.BatmanView
import com.batman.example.R
import com.batman.example.domain.service.CounterService
import com.batman.example.presentation.example.ExampleActivity
import com.batman.example.presentation.example.ExampleNavigation
import kotlinx.android.synthetic.main.example_1_view.view.*
import javax.inject.Inject

class Example1View: BatmanView<Long>() {
    override val layoutResource = R.layout.example_1_view

    /*
       Inject any necessary classes
    */
    @Inject lateinit var vm: Example1ViewModel
    @Inject lateinit var counterService: CounterService

    override fun di() {
        (activity as ExampleActivity).exampleComponent.inject(this)
    }

    override fun initialize(view: View) {
        view.to_example_2_view.setOnClickListener { vm.navigateTo(ExampleNavigation.TAGS.EXAMPLE_2_VIEW) }
        view.counter_button.setOnClickListener {
            if(view.counter_button.isChecked) {
                counterService.start()
            } else {
                counterService.stop()
                view.counter_text.text = "0"
            }
        }

        vm.onViewCreated(this)
    }

    override fun onData(data: Long) {
        batmanView!!.counter_text.text = data.toString()
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)

        vm.onViewDestroyed()
    }
}