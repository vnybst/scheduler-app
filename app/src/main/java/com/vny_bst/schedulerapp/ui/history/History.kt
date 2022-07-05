package com.vny_bst.schedulerapp.ui.history

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vny_bst.schedulerapp.data.model.ScheduleTask
import com.vny_bst.schedulerapp.ui.theme.Typography
import com.vny_bst.schedulerapp.ui.theme.firaSansFamily
import com.vny_bst.schedulerapp.viewmodel.TaskScheduleViewModel
import java.util.*

/**
 * Created by Vinay Singh Bisht on 20-Jan-22.
 */

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun History(taskScheduleViewModel: TaskScheduleViewModel) {

    val allTask = taskScheduleViewModel.allTask?.observeAsState()?.value ?: ArrayList()
    Log.e("All Task", "$allTask")

    Scaffold(
        content = {
            ListItems(allTask)
        }
    )
}

@Composable
fun ListItems(taskList: List<ScheduleTask>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(taskList.reversed()) {
            TaskView(it)
        }
    }
}

@Composable
fun TaskView(scheduleTask: ScheduleTask) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                scheduleTask.taskName?.let {
                    Text(
                        text = it.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                        },
                        fontFamily = firaSansFamily,
                        style = Typography.body1,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = scheduleTask.taskType.toString(),
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray,
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    History(TaskScheduleViewModel())
}
