package com.vny_bst.schedulerapp.ui.home

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vny_bst.schedulerapp.NavigationViewModel
import com.vny_bst.schedulerapp.data.schedulesList
import com.vny_bst.schedulerapp.ui.taskschedulerbottomsheet.MusicSchedulerScreen
import com.vny_bst.schedulerapp.ui.taskschedulerbottomsheet.ScheduleListItem
import com.vny_bst.schedulerapp.ui.taskschedulerbottomsheet.TaskSchedulerBottomSheet
import com.vny_bst.schedulerapp.ui.taskschedulerbottomsheet.TaskSchedulerScreen
import com.vny_bst.schedulerapp.viewmodel.TaskScheduleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Vinay Singh Bisht on 19-Jan-22.
 */

@Composable
@ExperimentalMaterialApi
fun Home(
    activity: Activity,
    navViewModel: NavigationViewModel = viewModel()

) {
    TaskSchedulerBottomSheet(mainContent = { scope, bottomSheetState ->
        ScheduleContent(scope, bottomSheetState, navViewModel)
    }, sheetContent = {
        val selectedPosition = navViewModel.clickPosition().observeAsState()
        BottomSheetScreens(position = selectedPosition.value , activity)
    })
}

@Composable
fun BottomSheetScreens(position: Int?, activity: Activity) {

    when (position) {

        0 -> {
            TaskSchedulerScreen(taskScheduleViewModel = TaskScheduleViewModel(activity.application))
        }

        1 -> {
            MusicSchedulerScreen(activity)
        }

        else -> {
            TaskSchedulerScreen(taskScheduleViewModel = TaskScheduleViewModel(activity.application))
        }
    }

}

@ExperimentalMaterialApi
@Composable
fun ScheduleContent(
    scope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
    viewModel: NavigationViewModel
) {
    Scaffold(
        content = {
            val schedules = remember { schedulesList }
            LazyColumn(contentPadding = PaddingValues(8.dp)) {
                itemsIndexed(
                    items = schedules,
                    itemContent = { index, item ->
                        ScheduleListItem(
                            position = index,
                            schedule = item
                        ) {
                            scope.launch {
                                viewModel.onClick(index)
                                bottomSheetState.show()
                            }
                        }
                    }
                )
            }
        }
    )
}