package com.asm.myapplication

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log

class MyJobService : JobService() {

    companion object {
        const val TAG = "MY_JOB_SERVICE"
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        val intent = Intent(this, ForegroundService::class.java)
        startForegroundService(intent)
        jobFinished(p0, false)
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d(TAG, "onStopJob() was called");
        return true;
    }

}