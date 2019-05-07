package com.batman.batdroid.domain.model

import com.batman.batdroid.domain.identity.BatmanId
import com.batman.batdroid.utils.BatmanIdGenerator
import java.io.Serializable
import java.nio.ByteBuffer
import java.util.*

/**
 * A [BatmanModel] is the base class for models in batdroid projects. Each
 * instantiation of a [BatmanModel] should be uniquely identifiable by its [BatmanId].
 */
abstract class BatmanModel(id: String = BatmanIdGenerator.generateId(), date: Date = Date(), version: Int = 0): Serializable {
    val id: BatmanId = BatmanId(id, date, version)

    /**
     * Used to put the attributes of a [BatmanModel] as bytes to an [output].
     */
    open fun toBytes(output: ByteBuffer) {}

    /**
     * Used to get the byte size of a [BatmanModel].
     */
    open fun size(): Int {
        return 0
    }

    companion object {
        /**
         * Used to read an id [String] from a [buf].
         */
        fun readId(buf: ByteBuffer): String {
            return UUID(buf.long, buf.long).toString()
        }

        /**
         * Used to write an id [String] to a [buf].
         */
        fun writeId(buf: ByteBuffer, id: String) {
            val uuid = UUID.fromString(id)
            buf.putLong(uuid.mostSignificantBits)
            buf.putLong(uuid.leastSignificantBits)
        }

        /**
         * Used to convert a UUID to an Int
         */
        fun idToInt(id : String) : Int {
            return UUID.fromString(id).leastSignificantBits.toInt()
        }

        /**
         * Used to convert an Int to a UUID
         */
        fun intToId(id : Int) : String {
            return UUID(0, id.toLong()).toString()
        }
    }
}