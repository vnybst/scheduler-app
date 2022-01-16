package com.vny_bst.schedulerapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vny_bst.schedulerapp.data.schedulesList
import com.vny_bst.schedulerapp.data.model.Schedules
import com.vny_bst.schedulerapp.ui.theme.Typography
import com.vny_bst.schedulerapp.ui.theme.firaSansFamily

/**
 * Created by Vinay Singh Bisht on 05-Oct-21.
 */

@Composable
fun ScheduleListItem(
    position: Int,
    schedule: Schedules,
    performAction: (position: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(
            corner = CornerSize(8.dp),
        )
    ) {
        Row(modifier = Modifier.clickable {
            performAction(position)
        }, verticalAlignment = Alignment.CenterVertically) {
            ScheduleListImage(schedules = schedule)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(id = schedule.name),
                    style = Typography.body1,
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = schedule.description),
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray,
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Composable
fun ScheduleListImage(schedules: Schedules) {
    Image(
        painter = painterResource(id = schedules.icon),
        colorFilter = ColorFilter.tint(Color.Gray),
        contentDescription = null,
        modifier = Modifier
            .padding(8.dp)
            .size(30.dp)
    )
}

@Preview
@Composable
fun ShowPreview() {
    ScheduleListItem(position = 0, schedule = schedulesList[0]) {

    }
}