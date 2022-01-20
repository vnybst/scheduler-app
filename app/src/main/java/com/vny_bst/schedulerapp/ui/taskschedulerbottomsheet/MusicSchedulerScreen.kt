package com.vny_bst.schedulerapp.ui.taskschedulerbottomsheet

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
 * Created by Vinay Singh Bisht on 13-Oct-21.
 */

@Composable
fun MusicSchedulerScreen() {

    var scheduleTime by remember { mutableStateOf("") }

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
                text = stringResource(id = R.string.schedule_music_description),
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
                value = scheduleTime,
                maxLines = 1,
                textStyle = TextStyle(fontWeight = FontWeight.Normal),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onValueChange = {
                    scheduleTime = it
                },
                label = { Text("Enter time(in min)") }
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            Button(
                onClick = {
/*                    val intent = Intent("com.android.music.musicservicecommand")
                    intent.putExtra("command", "pause")
                    activity.sendBroadcast(intent)*/
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Set Scheduler",
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.Normal
                )
            }

        }

    }

}