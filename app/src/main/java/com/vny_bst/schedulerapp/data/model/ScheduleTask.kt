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
    val taskName: String,
    val cat_id: Int,
    val time: Long,
    val taskType: TaskType? = null
)