package com.batman.batdroid.utils

import android.content.Context
import android.util.TypedValue

/**
 * Used to resolve theme attributes to their resource id.
 */
object BatmanResourceHelper {
    /**
     * Used to resolve theme attributes to their resource id.
     */
    fun getResourceForAttr(context: Context, attr: Int): Int {
        val tv = TypedValue()
        context.theme.resolveAttribute(attr, tv, true)
        return tv.resourceId
    }
}