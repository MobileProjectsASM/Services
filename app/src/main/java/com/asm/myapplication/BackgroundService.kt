package com.asm.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BackgroundService : Service() {

    companion object {
        const val TAG = "BACKGROUND_SERVICE"
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            while (true) {
                try {
                    Thread.sleep(1000)
                    Log.i(TAG, "Ejecutando peticion $startId")
                } catch (exception: Exception) {
                    Log.e(TAG, exception.stackTraceToString())
                }
            }
        }.start()
        return START_NOT_STICKY
    }
}