package com.asm.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class ForegroundService : Service() {

    companion object {
        const val TAG = "FOREGROUND_SERVICE"
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
        var cont = 0
        Thread {
            while (cont != 40) {
                try {
                    /*if (cont == 30) {
                        val intent2 = Intent(applicationContext, BackgroundService::class.java)
                        startService(intent2)
                    }*/
                    Log.i(TAG, "Ejecutando peticion $startId cont= $cont")
                    Thread.sleep(1000)
                    cont++
                } catch (exception: Exception) {
                    Log.e(TAG, exception.stackTraceToString())
                }
            }
            stopSelf()
        }.start()


        val mChannel =
            NotificationChannel("channel_example", "Channel 1", NotificationManager.IMPORTANCE_LOW)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)

        val notification = NotificationCompat.Builder(this, "channel_example")
            .setContentTitle("Titulo ejemplo")
            .setContentText("Contenido")
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .build()

        startForeground(1, notification)
        return START_NOT_STICKY
    }
}