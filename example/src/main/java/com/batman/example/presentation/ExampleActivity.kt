package com.batman.example.presentation

import android.Manifest
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toolbar
import com.batman.example.R
import com.batman.example.di.DaggerExampleComponent
import com.batman.example.di.ExampleApplication
import com.batman.example.di.ExampleComponent
import com.batman.example.di.ExampleModule
import com.batman.example.presentation.example1.Example1View
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import javax.inject.Inject

class ExampleActivity: Activity() {

    @Inject lateinit var exampleNavigation: ExampleNavigation

    lateinit var exampleComponent: ExampleComponent
    private lateinit var uiRouter: Router

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(savedInstanceState)
        di()
    }

    private fun initialize(savedInstanceState: Bundle?) {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)

        setContentView(R.layout.main_activity)

        val container = findViewById<FrameLayout>(R.id.main_frame)

        uiRouter = Conductor.attachRouter(this, container, savedInstanceState)
    }

    private fun di() {
        exampleComponent = DaggerExampleComponent.builder()
                .applicationComponent((application as ExampleApplication).applicationComponent)
                .exampleModule(ExampleModule(uiRouter, this))
                .build()
        exampleComponent.inject(this)
    }

    // Override this to add action bar menu items
    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    // Override this to respond to menu item selections
    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->  { exampleNavigation.showExampleView1() }
            R.id.item1 -> { }
            R.id.item2 -> { }
            R.id.item3 -> { }
        }

        return super.onOptionsItemSelected(item)
    }

    @Override
    override fun onResume() {
        super.onResume()
        uiRouter.setRoot(RouterTransaction.with(Example1View())
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
    }
}