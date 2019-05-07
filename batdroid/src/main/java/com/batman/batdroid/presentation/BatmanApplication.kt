package com.batman.batdroid.presentation

import android.app.Application
import android.os.Environment
import com.batman.batdroid.utils.BatmanTimeStamper
import java.io.*

/**
 * Used to provide a base implementation for an [Application] where a log file will be generated within
 * the [applicationDirectory] and the application can setup globally dependency injectable component(s).
 */
abstract class BatmanApplication: Application() {
    /**
     * Used to define the folder name to write the application log to.
     */
    abstract val application: String

    /**
     * Used to define the application log directory.
     */
    private val applicationDirectory = Environment.getExternalStorageDirectory().toString() + "/$application"

    /**
     * The name of the log file.
     */
    private val logFile = "log.txt"

    override fun onCreate() {
        super.onCreate()

        File(applicationDirectory).mkdirs()

        Thread.setDefaultUncaughtExceptionHandler { _, ex ->
            val stringWriter = StringWriter()
            val printWriter = PrintWriter(stringWriter)
            ex.printStackTrace(printWriter)
            ex.printStackTrace()
            val stackTrace = stringWriter.toString()

            logStackTrace(stackTrace)

            System.exit(1)
        }

        di()
    }

    /**
     * Used to initialize global [components][dagger.Component].
     *
     * [di] is automatically called after the application is [created][onCreate].
     *
     * Ex:
     * ```
     * myApplicationComponent = DaggerMyApplicationComponent.builder()
     *      .myApplicationModule(MyApplicationModule(this))
     *      .build()
     *
     * myApplicationComponent.inject(this)
     * ```
     */
    open fun di() {}

    /**
     * Used to log stack traces to the [logFile].
     */
    private fun logStackTrace(stackTrace: String) {
        try {
            val logFile = File(applicationDirectory, logFile)
            val writer = BufferedWriter(FileWriter(logFile, true))
            writer.write(BatmanTimeStamper.generateTimestamp())
            writer.newLine()
            writer.write(stackTrace)
            writer.write("**************************************")
            writer.newLine()
            writer.newLine()
            writer.newLine()
            writer.close()
        } catch (e: IOException) { // This will fail if file i/o permission were not granted
            e.printStackTrace()
        }
    }
}