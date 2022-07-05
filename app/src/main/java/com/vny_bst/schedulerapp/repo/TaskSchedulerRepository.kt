package com.vny_bst.schedulerapp.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vny_bst.schedulerapp.dao.TaskSchedulerDao
import com.vny_bst.schedulerapp.data.model.ScheduleTask
import kotlinx.coroutines.*

/**
 * Created by Vinay Singh Bisht on 07-Jan-22.
 */
class TaskSchedulerRepository(private val taskSchedulerDao: TaskSchedulerDao) {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    val taskResult = MutableLiveData<List<ScheduleTask>>()
    val allTask: LiveData<List<ScheduleTask>> = taskSchedulerDao.getAllSchedules()

    fun insert(task: ScheduleTask) {
        coroutineScope.launch(Dispatchers.IO) {
            val count = taskSchedulerDao.insert(task)
            Log.e("TaskDB","${count}")
        }
    }

    fun getTask() {
        coroutineScope.launch(Dispatchers.Main) {
            taskResult.value = asyncFind()
        }
    }

    private suspend fun asyncFind(): List<ScheduleTask>? =
        coroutineScope.async(Dispatchers.IO) {
            return@async taskSchedulerDao.getAllSchedules().value
        }.await()

}