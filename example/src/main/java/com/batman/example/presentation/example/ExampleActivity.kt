package com.batman.example.presentation.example

import android.Manifest
import android.view.MenuItem
import com.batman.batdroid.presentation.activity.BatmanActivity
import com.batman.example.R
import com.batman.example.di.DaggerExampleComponent
import com.batman.example.di.ExampleComponent
import com.batman.example.di.ExampleModule
import com.batman.example.presentation.ExampleApplication
import com.batman.example.presentation.example.example1.Example1View
import javax.inject.Inject

class ExampleActivity: BatmanActivity<Long>() {

    @Inject lateinit var exampleNavigation: ExampleNavigation

    lateinit var exampleComponent: ExampleComponent

    override val layoutResource = R.layout.main_activity
    override val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    override val menuResource = R.menu.main_menu
    override val rootView = Example1View()
    override val rootViewTag = "example-1-view"

    override fun di() {
        exampleComponent = DaggerExampleComponent.builder()
                .applicationComponent((application as ExampleApplication).applicationComponent)
                .exampleModule(ExampleModule(uiRouter!!, this))
                .build()
        exampleComponent.inject(this)
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->  { exampleNavigation.navigateTo(ExampleNavigation.TAGS.EXAMPLE_1_VIEW) }
            R.id.item1 -> { }
            R.id.item2 -> { }
            R.id.item3 -> { }
        }

        return super.onOptionsItemSelected(item)
    }
}