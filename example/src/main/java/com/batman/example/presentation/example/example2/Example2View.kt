package com.batman.example.presentation.example.example2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.batman.example.R
import com.batman.example.domain.model.ExampleStringModel
import com.batman.example.presentation.example.ExampleActivity
import com.batman.example.presentation.example.ExampleNavigation
import com.batman.example.presentation.misc.BatmanView
import kotlinx.android.synthetic.main.example_2_view.view.*
import javax.inject.Inject

class Example2View: BatmanView<ExampleStringModel>() {

    /*
       Inject any necessary classes
    */
    @Inject lateinit var vm: Example2ViewModel

    // <editor-fold desc="Execute in response to the view's creation">
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        batmanView = inflater.inflate(R.layout.example_2_view, container, false)

        di()
        initialize(batmanView!!)

        return batmanView!!
    }

    override fun di() {
        (activity as ExampleActivity).exampleComponent.inject(this)
    }

    override fun initialize(view: View) {
        view.to_example_1_view.setOnClickListener { vm.navigateTo(ExampleNavigation.TAGS.EXAMPLE_1_VIEW) }
        view.example_string_edit_text.setOnFocusChangeListener { _, _ ->
            if (model != null) {
                model!!.exampleString = view.example_string_edit_text.text.toString()
                vm.updateDatastore()
            }
        }

        vm.onViewCreated(this)
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)

        vm.onViewDestroyed()
    }

    override fun onData(data: ExampleStringModel) {
        model = data
        batmanView!!.example_string_edit_text.setText(data.exampleString)
    }
}