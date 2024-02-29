package com.asm.myapplication

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.i("Permission: ", "Granted")
        } else {
            Log.i("Permission: ", "Denied")
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)

        scheduleJob()
    }

    private fun scheduleJob() {
        val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

        // The JobService that we want to run
        val name = ComponentName(this, MyJobService::class.java)

        // Schedule the job
        val result: Int = jobScheduler.schedule(getJobInfo(name))

        // If successfully scheduled, log this thing
        if (result == JobScheduler.RESULT_SUCCESS) {
            Log.d("Main", "Scheduled job successfully!")
        }
    }

    private fun getJobInfo(name: ComponentName): JobInfo {
        val interval: Long = TimeUnit.MINUTES.toMillis(15)
        val isPersistent = true // persist through boot
        val networkType = JobInfo.NETWORK_TYPE_ANY // Requires some sort of connectivity
        return JobInfo.Builder(123, name)
            .setPeriodic(interval)
            .setRequiredNetworkType(networkType)
            .setPersisted(isPersistent)
            .build()
    }
}
