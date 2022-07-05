package com.vny_bst.schedulerapp.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


/**
 * Created by Vinay Singh Bisht on 07-Jan-22.
 */
class SchedulerWorkManager(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {

        return Result.success()
    }


}