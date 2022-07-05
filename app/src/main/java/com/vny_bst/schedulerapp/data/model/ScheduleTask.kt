package com.vny_bst.schedulerapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vny_bst.schedulerapp.util.TaskType

/**
 * Created by Vinay Singh Bisht on 07-Jan-22.
 */
@Entity(tableName = "tbl_schedules")
data class ScheduleTask(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var taskName: String? = null,
    var time: Long? = 0L,
    var taskType: TaskType? = null
)