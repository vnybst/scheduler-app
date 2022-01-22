package com.vny_bst.schedulerapp.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vny_bst.schedulerapp.data.model.ScheduleTask
import com.vny_bst.schedulerapp.util.Constants

/**
 * Created by Vinay Singh Bisht on 07-Jan-22.
 */

@Database(
    entities = [ScheduleTask::class],
    version = Constants.db_version,
    exportSchema = true,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        @Volatile
        private var dbInstance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            val tempInstance = dbInstance

            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constants.db_name
                ).build()
                dbInstance = instance
                return instance
            }
        }

    }

}