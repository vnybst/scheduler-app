package com.vny_bst.schedulerapp.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Vinay Singh Bisht on 07-Jan-22.
 */
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val db_name = "task_scheduler"

        @Volatile
        private var dbInstance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            val tempInstance = dbInstance

            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    db_name
                ).fallbackToDestructiveMigration().build()
                dbInstance = instance
                return instance
            }
        }

    }

}