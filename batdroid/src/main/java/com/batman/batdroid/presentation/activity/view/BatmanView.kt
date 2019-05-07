package com.batman.batdroid.presentation.activity.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.batman.batdroid.domain.model.BatmanModel
import com.bluelinelabs.conductor.Controller
import io.reactivex.disposables.CompositeDisposable

/**
 * # BatmanView
 * A [BatmanView] is a minimal implementation of a [Controller] by [BlueLineLabs](https://github.com/bluelinelabs/Conductor).
 *
 * [BatmanViews][BatmanView] are a replacement for [Fragments][android.support.v4.app.Fragment] and are responsible
 * for presenting information to the user as well as receiving input from the user. There can be many [BatmanViews][BatmanView]
 * to a [BatmanActivity][com.batman.batdroid.presentation.activity.BatmanActivity] which can be navigated to via the
 * [navigateTo][com.batman.batdroid.presentation.activity.view.BatmanViewModel.navigateTo] function of a
 * [BatmanViewModel].
 *
 * [Life Cycle](https://raw.githubusercontent.com/bluelinelabs/Conductor/develop/docs/Controller%20Lifecycle.jpg)
 *
 */
abstract class BatmanView<Data>: Controller() {
    /**
     * Used to hold a reference to the [View] that Android will present to the user.
     *
     * Example to reference an element defined in the [layoutResource] of a [batmanView]:
     * ```
     * val myButton = batmanView.my_button
     * ```
     * See Also: [initialize][initialize]
     */
    open var batmanView: View? = null

    /**
     * Used to store a model which can be used to populate the [batmanView].
     */
    open var model: Data? = null

    /**
     * Used to hold a reference to a collection of [Disposables][io.reactivex.disposables.Disposable]
     * which will be cleaned up when the view is [destroyed][onDestroyView].
     */
    open var disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Used to provide the layout resource that will be inflated when the view is [created][onCreateView]
     */
    abstract val layoutResource : Int

    /**
     * Used to pass and extract a [Bundle] to a [BatmanView] when it is created so that it
     * does not need to query for its [model] to populate [batmanView] elements.
     */
    abstract class Bundler<Model: BatmanModel> {
        /**
         * Requires that a key is defined [to bundle][toBundle] and [extract][fromBundle] a [BatmanModel].
         */
        abstract val key: String

        /**
         * Used to package a [BatmanModel] into a [Bundle].
         */
        open fun toBundle(model: Model): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(key, model)
            return bundle
        }

        /**
         * Used to extract a [BatmanModel] (or null) from a [Bundle].
         */
        open fun fromBundle(bundle: Bundle): Model? {
            val serializable = bundle.getSerializable(key)
            return if (serializable is BatmanModel) (serializable as Model) else null
        }
    }

    /**
     * Used to inflate the [view][batmanView], [dependency inject][di], and [initialize] view element data and
     * [onClickListeners][View.setOnClickListener].
     *
     * [onCreateView] is automatically called after the construction of a [BatmanView].
     *
     * [Life Cycle](https://raw.githubusercontent.com/bluelinelabs/Conductor/develop/docs/Controller%20Lifecycle.jpg)
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        if (batmanView == null) {
            batmanView = inflater.inflate(layoutResource, container, false)
        }

        di()
        initialize(batmanView!!)

        return batmanView!!
    }

    /**
     * Used to deconstruct the [view][batmanView] and [dispose][CompositeDisposable.dispose] any [disposables].
     *
     * [onDestroyView] is automatically called when the [batmanView] reference is released.
     *
     * [Life Cycle](https://raw.githubusercontent.com/bluelinelabs/Conductor/develop/docs/Controller%20Lifecycle.jpg)
     */
    override fun onDestroyView(view: View) {
        super.onDestroyView(view)

        if(!disposables.isDisposed) {
            disposables.dispose()
        }

        batmanView = null
    }


    /**
     * Used to setup dependency injection via a [Dagger2](https://google.github.io/dagger/users-guide) [component][dagger.Component]
     * [injection][dagger.MembersInjector.injectMembers].
     *
     * [di] is automatically called after the inflation of a [BatmanView].
     */
    open fun di() {}

    /**
     * Used to initialize [disposables], load data into the [batmanView], and setup [batmanView] element
     * [onClickListeners][View.setOnClickListener].
     *
     * [initialize] is automatically called after [di].
     */
    abstract fun initialize(view: View)

    /**
     * Used to receive and respond to [data] updates from a [BatmanViewModel].
     */
    open fun onData(data: Data) {}
}