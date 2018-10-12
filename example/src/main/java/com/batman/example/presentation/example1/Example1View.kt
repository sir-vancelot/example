package com.batman.example.presentation.example1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.batman.example.R
import com.batman.example.presentation.ExampleActivity
import com.bluelinelabs.conductor.Controller
import kotlinx.android.synthetic.main.example_1_view.view.*
import javax.inject.Inject

class Example1View: Controller() {

    @Inject lateinit var vm: Example1ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.example_1_view, container, false)

        initialize(view)
        di()

        return view
    }

    private fun initialize(view: View) {
        view.to_example_2_view.setOnClickListener { vm.showExample2View() }
    }

    private fun di() {
        (activity as ExampleActivity).exampleComponent.inject(this)
    }
}