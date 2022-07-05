package com.vny_bst.schedulerapp.ui.taskschedulerbottomsheet

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vny_bst.schedulerapp.R
import com.vny_bst.schedulerapp.alarm.AlarmReceiver
import com.vny_bst.schedulerapp.data.model.ScheduleTask
import com.vny_bst.schedulerapp.ext.formatToDateTime
import com.vny_bst.schedulerapp.ext.getDate
import com.vny_bst.schedulerapp.ui.components.datePicker
import com.vny_bst.schedulerapp.ui.components.timePicker
import com.vny_bst.schedulerapp.ui.theme.Typography
import com.vny_bst.schedulerapp.ui.theme.firaSansFamily
import com.vny_bst.schedulerapp.util.TaskType
import com.vny_bst.schedulerapp.viewmodel.TaskScheduleViewModel
import kotlinx.coroutines.*

/**
 * Created by Vinay Singh Bisht on 05-Oct-21.
 */

@Composable
fun TaskSchedulerScreen(taskScheduleViewModel: TaskScheduleViewModel) {

    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterVertically)
        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            Text(
                text = stringResource(id = R.string.schedule_task),
                style = Typography.body1,
                fontFamily = firaSansFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = taskName,
                maxLines = 1,
                textStyle = TextStyle(fontWeight = FontWeight.Normal),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onValueChange = {
                    taskName = it
                },
                label = { Text(stringResource(R.string.task_name)) }
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        datePicker(context) { date ->
                            timePicker(context, date) {
                                it
                                    .formatToDateTime()
                                    ?.let { formattedDate ->
                                        taskDescription = formattedDate
                                    }
                            }
                        }
                    },
                value = taskDescription,
                onValueChange = {
                    taskDescription = it
                },
                textStyle = TextStyle(fontWeight = FontWeight.Normal),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                label = { Text(stringResource(R.string.reminder_date)) },
                enabled = false
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            Button(
                onClick = {
                    coroutineScope.launch(coroutineScope.coroutineContext , CoroutineStart.DEFAULT) {
                        withContext(Dispatchers.IO) {
                            initAlarmManger(
                                taskDescription,
                                context,
                                taskName,
                                taskScheduleViewModel
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.set_reminder),
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }

}

suspend fun initAlarmManger(
    time: String,
    context: Context,
    task: String,
    taskScheduleViewModel: TaskScheduleViewModel
) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val alarmTimeAtUTC = time.getDate()?.time
    val id = System.currentTimeMillis()
    intent.action = "Set_Reminder"
    intent.putExtra("task", task)
    val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        PendingIntent.getBroadcast(context, id.toInt(), intent, PendingIntent.FLAG_IMMUTABLE)
    } else {
        PendingIntent.getBroadcast(context, id.toInt(), intent, 0)
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (alarmTimeAtUTC != null) {
            Log.e("Alarm", "$alarmTimeAtUTC")
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                alarmTimeAtUTC,
                pendingIntent
            )
            insertTaskToDB(
                taskScheduleViewModel,
                task,
                alarmTimeAtUTC,
                TaskType.TaskReminder
            )
        }
    }
}

suspend fun insertTaskToDB(
    taskScheduleViewModel: TaskScheduleViewModel,
    taskName: String,
    taskTime: Long,
    taskType: TaskType
) {
    val scheduleTask = ScheduleTask()
    scheduleTask.taskName = taskName
    scheduleTask.time = taskTime
    scheduleTask.taskType = taskType
    Log.e("TaskData", "${scheduleTask.taskType},${scheduleTask.taskName},${scheduleTask.time},")
    taskScheduleViewModel.insertTask(scheduleTask)
}
