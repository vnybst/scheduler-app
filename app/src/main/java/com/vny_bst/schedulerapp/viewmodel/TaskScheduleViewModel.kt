package com.vny_bst.schedulerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vny_bst.schedulerapp.data.model.ScheduleTask
import com.vny_bst.schedulerapp.db.AppDatabase
import com.vny_bst.schedulerapp.repo.TaskSchedulerRepository
import kotlinx.coroutines.withContext

class TaskScheduleViewModel(application: Application?=null) : AndroidViewModel(application!!) {
    private val dao = application?.let { AppDatabase.getDatabase(it).taskSchedulerDao() }
    private val taskRepo = dao?.let { TaskSchedulerRepository(it) }

    var allTask = taskRepo?.allTask
    var taskResult = taskRepo?.taskResult

    suspend fun insertTask(task: ScheduleTask) {
        return withContext(viewModelScope.coroutineContext) {
            taskRepo?.insert(task)
        }
    }

    suspend fun getTask(task: ScheduleTask) {
        return withContext(viewModelScope.coroutineContext) {
            taskRepo?.getTask()
        }
    }
}