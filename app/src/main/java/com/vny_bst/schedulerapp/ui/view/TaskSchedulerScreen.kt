package com.vny_bst.schedulerapp.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vny_bst.schedulerapp.R
import com.vny_bst.schedulerapp.ui.theme.Typography
import com.vny_bst.schedulerapp.ui.theme.firaSansFamily

/**
 * Created by Vinay Singh Bisht on 05-Oct-21.
 */

@Composable
fun TaskSchedulerScreen() {

    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }

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
                label = { Text("Task name") }
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = taskDescription,
                onValueChange = {
                    taskDescription = it
                },
                textStyle = TextStyle(fontWeight = FontWeight.Normal),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                label = { Text("Reminder time") }
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
                    text = "Done",
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}