package com.batman.batdroid.di

import javax.inject.Scope

/**
 * The [BatmanApplicationScope] is a [Dagger 2][dagger]
 * [Singleton][javax.inject.Singleton] [Component][dagger.Component] [Scope][javax.inject.Scope]
 * intended to be used by [components][dagger.Component] held by a
 * [BatmanApplication][com.batman.batdroid.presentation.BatmanApplication]. Provisions made with
 * this scope in the [Module][dagger.Module] corresponding to a [Component][dagger.Component] held
 * in [BatmanApplicationScope] will be kept in memory while their application is alive.
 */
@Scope
@kotlin.annotation.MustBeDocumented
@kotlin.annotation.Retention(value = AnnotationRetention.RUNTIME)
annotation class BatmanApplicationScope
