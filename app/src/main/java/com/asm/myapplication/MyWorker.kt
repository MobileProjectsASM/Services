package com.asm.myapplication

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class MyWorker(
    context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    companion object {
        const val TAG = "MY_WORKER"
    }
    override fun doWork(): Result {

        Log.i(TAG, "Executing work")
        val result = try {
            Thread.sleep(TimeUnit.MINUTES.toMillis(3))
            Log.i(TAG, "Finish work")
            Result.success()
        } catch (exception: Exception) {
            Log.e(TAG, exception.stackTraceToString())
            Result.failure()
        }
        return result
    }
}