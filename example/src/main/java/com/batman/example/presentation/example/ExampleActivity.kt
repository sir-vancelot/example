package com.batman.example.presentation.example

import android.Manifest
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.batman.example.R
import com.batman.example.di.DaggerExampleComponent
import com.batman.example.di.ExampleComponent
import com.batman.example.di.ExampleModule
import com.batman.example.presentation.ExampleApplication
import com.batman.example.presentation.example.example1.Example1View
import com.batman.example.presentation.misc.BatmanActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.RouterTransaction
import javax.inject.Inject

class ExampleActivity: BatmanActivity() {

    /*
       Inject any necessary classes
    */
    @Inject lateinit var exampleNavigation: ExampleNavigation

    /*
       Setup local variables
    */
    lateinit var exampleComponent: ExampleComponent

    // <editor-fold desc="Execute in response to the activity's creation">
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(savedInstanceState)
        di()
    }
    // </editor-fold>

    override fun initialize(savedInstanceState: Bundle?) {
        // <editor-fold desc="Request permissions because this is the main activity">
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        // </editor-fold>

        setContentView(R.layout.main_activity)

        val container = findViewById<FrameLayout>(R.id.main_frame)

        uiRouter = Conductor.attachRouter(this, container, savedInstanceState)
    }

    override fun di() {
        exampleComponent = DaggerExampleComponent.builder()
                .applicationComponent((application as ExampleApplication).applicationComponent)
                .exampleModule(ExampleModule(uiRouter!!, this))
                .build()
        exampleComponent.inject(this)
    }

    // <editor-fold desc="Execute in response to the options menu's creation to add menu items">
    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }
    // </editor-fold>

    // <editor-fold desc="Execute in response to an option item being selected">
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
    // </editor-fold>

    // <editor-fold desc="Execute in response to the activity resuming (NOTE: This occurs after the activity is started and when an activity resumed after being paused">
    @Override
    override fun onResume() {
        super.onResume()

        if (uiRouter != null && !uiRouter!!.hasRootController()) {
            uiRouter!!.setRoot(RouterTransaction.with(Example1View()))
        }
    }
    // </editor-fold>
}