package com.batman.batdroid.utils

import java.security.MessageDigest

/**
 * Used to get the sha512 hash for a [String].
 */
fun String.sha512(): String {
    return this.hashWithAlgorithm("SHA-512")
}

/**
 * Used to get the hash for a [String] by the provided [algorithm].
 */
private fun String.hashWithAlgorithm(algorithm: String): String {
    val digest = MessageDigest.getInstance(algorithm)
    val bytes = digest.digest(this.toByteArray(Charsets.UTF_8))
    return bytes.fold("") { str, it -> str + "%02x".format(it) }
}