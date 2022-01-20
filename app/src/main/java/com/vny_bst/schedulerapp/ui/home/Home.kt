package com.vny_bst.schedulerapp.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vny_bst.schedulerapp.NavigationViewModel
import com.vny_bst.schedulerapp.R
import com.vny_bst.schedulerapp.data.schedulesList
import com.vny_bst.schedulerapp.ui.theme.firaSansFamily
import com.vny_bst.schedulerapp.ui.taskschedulerbottomsheet.MusicSchedulerScreen
import com.vny_bst.schedulerapp.ui.taskschedulerbottomsheet.ScheduleListItem
import com.vny_bst.schedulerapp.ui.taskschedulerbottomsheet.TaskSchedulerBottomSheet
import com.vny_bst.schedulerapp.ui.taskschedulerbottomsheet.TaskSchedulerScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Vinay Singh Bisht on 19-Jan-22.
 */

@Composable
@ExperimentalMaterialApi
fun Home(
    navViewModel: NavigationViewModel = viewModel()
) {
    TaskSchedulerBottomSheet(mainContent = { scope, bottomSheetState ->
        ScheduleContent(scope, bottomSheetState, navViewModel)
    }, sheetContent = {
        val selectedPosition = navViewModel.clickPosition().observeAsState()
        BottomSheetScreens(position = selectedPosition.value)
    })
}

@Composable
fun BottomSheetScreens(position: Int?) {

    when (position) {

        0 -> {
            TaskSchedulerScreen()
        }

        1 -> {
            MusicSchedulerScreen()
        }

        else -> {
            TaskSchedulerScreen()
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