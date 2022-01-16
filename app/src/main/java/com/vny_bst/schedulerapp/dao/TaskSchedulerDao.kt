package com.vny_bst.schedulerapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.vny_bst.schedulerapp.data.model.ScheduleTask

/**
 * Created by Vinay Singh Bisht on 07-Jan-22.
 */
@Dao
interface TaskSchedulerDao {

    @Insert(onConflict = REPLACE)
    fun insert(scheduler: ScheduleTask)

    @Query("SELECT * FROM tbl_schedules")
    fun getAllSchedules(): LiveData<List<ScheduleTask>>

    @Delete
    fun deleteSchedule(scheduler: ScheduleTask)

}