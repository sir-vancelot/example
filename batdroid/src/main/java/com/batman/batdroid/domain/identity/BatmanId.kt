package com.batman.batdroid.domain.identity

import java.io.Serializable
import java.util.*

/**
 * A [BatmanId] is a class containing a string id ([unique]), a date created ([created]), and a version ([version])
 * that is intended to be used in a [BatmanModel][com.batman.batdroid.domain.model.BatmanModel] to uniquely identify
 * a [BatmanModel][com.batman.batdroid.domain.model.BatmanModel].
 */
open class BatmanId(val unique: String = "0", val created: Date = Date(), val version: Int = 0)
    : Serializable {

    /**
     * Used to get a unique identifier for every [BatmanId].
     */
    override fun hashCode(): Int {
        return Objects.hash(unique)
    }

    /**
     * Used to determine if two [BatmanIds][BatmanId] are equivalent.
     */
    override fun equals(other: Any?): Boolean {
            return this.hashCode() == other?.hashCode()
    }
}
