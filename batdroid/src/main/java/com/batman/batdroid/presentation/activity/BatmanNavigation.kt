package com.batman.batdroid.presentation.activity

import android.os.Bundle
import com.bluelinelabs.conductor.Router

/**
 * Used navigate from one [BatmanView][com.batman.batdroid.presentation.activity.view.BatmanView] under
 * a [BatmanActivity] to another [BatmanView][com.batman.batdroid.presentation.activity.view.BatmanView] or
 * another [BatmanActivity].
 */
abstract class BatmanNavigation(protected val uiRouter: Router? = null) {
    /**
     * Used to navigate between [BatmanViews][com.batman.batdroid.presentation.activity.view.BatmanView]
     * or another [BatmanActivity] based on a string [tag]. A [bundle] can also be passed to start a
     * new [BatmanView][com.batman.batdroid.presentation.activity.view.BatmanView] or [BatmanActivity]
     * with a model held in the previous [BatmanView][com.batman.batdroid.presentation.activity.view.BatmanView] or [BatmanActivity].
     *
     * Ex:
     * ```
     * override fun navigateTo(tag: String, bundle: Bundle?) {
     *      when(tag) {
     *          MY_ROOT_VIEW -> showMyRootView()
     *          MY_SECOND_VIEW -> showMySecondView(bundle!!)
     *          MY_ACTIVITY -> showMyActivity(bundle!!)
     *      }
     * }
     *
     * companion object: BatmanNavigation.TAGS() {
     *      const val MY_ROOT_VIEW = "my-root-view"
     *      const val MY_SECOND_VIEW = "my-second-view"
     *      const val MY_ACTIVITY = "my-activity"
     * }
     *
     * private fun showMyRootView() {
     *      uiRouter?.popToRoot()
     * }
     *
     * private fun showMySecondView(bundle: Bundle) {
     *      if (uiRouter?.getControllerWithTag(MY_SECOND_VIEW) == null) {
     *          uiRouter?.pushController(RouterTransaction.with(MySecondView(bundle))
     *                  .tag(MY_SECOND_VIEW)
     *                  .pushChangeHandler(HorizontalChangeHandler())
     *                  .popChangeHandler(HorizontalChangeHandler()))
     *      }
     * }
     *
     * private fun showMyActivity(bundle: Bundle) {
     *      val intent = Intent(activity, MyActivity::class.java)
     *      intent.putExtra(MyActivity.key, bundle)
     *
     *      activity.startActivity(intent)
     *      activity.finish()
     * }
     * ```
     */
    open fun navigateTo(tag: String, bundle: Bundle? = null){ }

    /**
     * Used to define a list of tags to associate with views added to the [uiRouter] back stack.
     *
     * Ex:
     * ```
     * companion object: BatmanNavigation.TAGS() {
     *      const val MY_ROOT_VIEW = "my-root-view"
     *      const val MY_SECOND_VIEW = "my-second-view"
     *      const val MY_ACTIVITY = "my-activity"
     * }
     * ```
     */
    abstract class TAGS
}