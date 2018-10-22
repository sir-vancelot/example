package com.batman.example.presentation

import android.app.Application
import android.os.Environment
import com.batman.example.di.ApplicationComponent
import com.batman.example.di.ApplicationModule
import com.batman.example.di.DaggerApplicationComponent
import com.batman.example.presentation.misc.BatmanTimeStamper
import java.io.*

class ExampleApplication: Application() {
    /*
       Allow other classes to inject the same component
    */
    lateinit var applicationComponent: ApplicationComponent

    /*
       Setup local variables
    */
    private val logDirectory = Environment.getExternalStorageDirectory().toString() + "/Example"
    private val logFile = "log.txt"

    // <editor-fold desc="Execute in response to the application's creation">
    @Override
    override fun onCreate() {
        super.onCreate()

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
    // </editor-fold>

    // <editor-fold desc="Inject dependencies">
    private fun di(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }
    // </editor-fold>

    // <editor-fold desc="Log stack trace to a file">
    private fun logStackTrace(stackTrace: String) {
        try {
            val logFile = File(logDirectory, logFile)
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
    // </editor-fold>
}