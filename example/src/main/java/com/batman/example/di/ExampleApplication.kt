package com.batman.example.di

import android.app.Application
import android.os.Environment
import java.io.*

class ExampleApplication: Application() {

    // Public var so other classes can use the same module
    lateinit var applicationComponent: ApplicationComponent

    @Override
    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { thread, ex ->
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

    private fun di(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }

    private fun logStackTrace(stackTrace: String) {
        val dir = File(Environment.getExternalStorageDirectory(), "Example")
        try {
            val logFile = File(dir, "log.txt")
            val writer = BufferedWriter(FileWriter(logFile, true))
            writer.write(TimeStamper.generateTimestamp())
            writer.newLine()
            writer.write(stackTrace)
            writer.write("**************************************")
            writer.newLine()
            writer.newLine()
            writer.newLine()
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}