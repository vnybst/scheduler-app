package com.vny_bst.schedulerapp.data

import com.vny_bst.schedulerapp.R
import com.vny_bst.schedulerapp.data.model.Schedules

/**
 * Created by Vinay Singh Bisht on 05-Oct-21.
 */

    val schedulesList = arrayListOf(
        Schedules(
            1, R.string.schedule_task,
            R.drawable.ic_baseline_add_task_24, R.string.schedule_task_description
        ),
        Schedules(
            2, R.string.schedule_music,
            R.drawable.ic_baseline_music_note_24, R.string.schedule_music_description
        ),
        Schedules(
            3, R.string.schedule_message,
            R.drawable.ic_baseline_message_24, R.string.schedule_sms_description
        ),
        Schedules(
            4, R.string.schedule_call,
            R.drawable.ic_baseline_call_24, R.string.schedule_call_description
        ),
        Schedules(
            5, R.string.schedule_network_off,
            R.drawable.ic_baseline_network_wifi_24, R.string.schedule_mobile_data_description
        ),
        Schedules(
            7, R.string.schedule_clear_all_apps,
            R.drawable.ic_baseline_clear_all_24, R.string.schedule_clear_apps_description
        ),
    )
