package com.vny_bst.schedulerapp.ui.taskschedulerbottomsheet

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
import com.vny_bst.schedulerapp.ext.formatToDDMMMYYYY
import com.vny_bst.schedulerapp.ext.formatToDateTime
import com.vny_bst.schedulerapp.ui.components.datePicker
import com.vny_bst.schedulerapp.ui.components.timePicker
import com.vny_bst.schedulerapp.ui.theme.Typography
import com.vny_bst.schedulerapp.ui.theme.firaSansFamily

/**
 * Created by Vinay Singh Bisht on 05-Oct-21.
 */

@Composable
fun TaskSchedulerScreen() {

    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    val context = LocalContext.current

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
                modifier = Modifier
                    .fillMaxWidth(),
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
                                date
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
                onClick = { },
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
