package com.batman.batdroid.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.FrameLayout
import com.batman.batdroid.R
import com.batman.batdroid.presentation.activity.view.BatmanView
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import io.reactivex.disposables.CompositeDisposable

/**
 * Used to provide a base implementation and outline for [creating][onCreate], [initializing][initialize],
 * setting up [dependency injection][di] for, and handling various [AppCompatActivity] lifecycle events.
 *
 * [Lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle)
 */
abstract class BatmanActivity<Data: Any>: AppCompatActivity() {
    /**
     * Used to hold a reference to a [Router] so that [views][BatmanView] can navigate to one another
     * via a [BatmanNavigation].
     */
    protected lateinit var uiRouter: Router

    /**
     * Used to define the layout resource that will be used to define containers for a [BatmanView]
     * in an activity.
     */
    protected abstract val layoutResource: Int?

    /**
     * Used to define the Android permissions required for an activity to function.
     *
     * Ex:
     * ```
     * override val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
     * ```
     */
    protected abstract val permissions: Array<String>

    /**
     * Used to define the menu options for an activity.
     */
    protected abstract val menuResource: Int?

    /**
     * Used to define the initial view to be presented when a [BatmanActivity] starts.
     */
    protected abstract val rootView: BatmanView<Data>?

    /**
     * Used to tag the root view so that a developer can test if a view is on the [Router] back stack
     * before adding a copy.
     */
    protected abstract val rootViewTag: String

    /**
     * Used to hold a reference to a collection of [Disposables][io.reactivex.disposables.Disposable]
     * which will be cleaned up when the activity is [destroyed][onDestroy].
     */
    protected var disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (layoutResource != null) {
            setContentView(layoutResource!!)
        }

        requestPermissions()
        initialize(savedInstanceState)
        di()
        adjustRootView()
    }

    /**
     * Used to make the permissions request to the user.
     */
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    /**
     * Used to initialize the toolbar as well as start any [services][com.batman.batdroid.domain.service.BatmanService]
     * or setup any [disposables][io.reactivex.disposables.Disposable].
     *
     * Ex:
     * ```
     * myService.start()
     *
     * myQueryHandler.query(MyQuery()).subscribe {
     *      menuItem.setSelected(it.menuItem)
     * }
     *
     * onNotificationPublished
     *      .filter(it is MyNotification)
     *      .map(it as MyNotification)
     *      .subscribe {
     *          menuItem.setSelected(it.menuItem)
     *      }
     *
     * onEventPublished
     *      .filter(it is MyEvent)
     *      .map(it as MyEvent)
     *      .subscribe {
     *          if (it.isStart) {
     *              myService.start()
     *          } else {
     *              myService.stop()
     *          }
     *      }
     * ```
     */
    open fun initialize(savedInstanceState: Bundle?) {
        autoInitializeToolbar(R.id.toolbar)
        autoInitializeRouter(R.id.main_frame, savedInstanceState)
    }

    /**
     * Used to auto initialize the [support action toolbar][setSupportActionBar].
     */
    private fun autoInitializeToolbar(@IdRes toolbarId: Int) {
        val toolbar = findViewById<Toolbar>(toolbarId)
        setSupportActionBar(toolbar)
    }

    /**
     * Used to auto initialize the [uiRouter].
     */
    private fun autoInitializeRouter(@IdRes containerId: Int, savedInstanceState: Bundle?) {
        val container = findViewById<FrameLayout>(containerId)
        uiRouter = Conductor.attachRouter(this, container, savedInstanceState)
    }

    /**
     * Used to setup dependency injection via a [Dagger2](https://google.github.io/dagger/users-guide) [component][dagger.Component]
     * [injection][dagger.MembersInjector.injectMembers].
     *
     * [di] is automatically called after the [creation][onCreate] of a [BatmanActivity].
     *
     * Ex:
     * ```
     * myComponent = DaggerMyComponent.builder()
     *      .applicationComponent((application as MyApplication).applicationComponent)
     *      .myModule(MyModule(uiRouter, this))
     *      .build()
     *
     * myComponent.inject(this)
     * ```
     */
    open fun di() {}

    /**
     * Used to dynamically adjust the [rootView] after dependency injection and before [onCreate] finishes.
     * Any disposables initialized in [adjustRootView] should be made as blocking calls to ensure the
     * [rootView] is adjusted before the view is created.
     */
    open fun adjustRootView() {}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        if (menuResource != null) {
            menuInflater.inflate(menuResource!!, menu)
        }
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return true
    }

    @Override
    override fun onResume() {
        super.onResume()

        if (!uiRouter.hasRootController() && rootView != null) {
            uiRouter.setRoot(RouterTransaction.with(rootView!!)
                    .tag(rootViewTag))
        }
    }

    /**
     * Used to force a restart of a [BatmanActivity].
     */
    fun restart() {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()

        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}