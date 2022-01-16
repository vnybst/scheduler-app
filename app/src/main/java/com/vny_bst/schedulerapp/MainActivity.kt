package com.vny_bst.schedulerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vny_bst.schedulerapp.data.schedulesList
import com.vny_bst.schedulerapp.ui.theme.SchedulerAppTheme
import com.vny_bst.schedulerapp.ui.theme.firaSansFamily
import com.vny_bst.schedulerapp.ui.view.MusicSchedulerScreen
import com.vny_bst.schedulerapp.ui.view.ScheduleListItem
import com.vny_bst.schedulerapp.ui.view.TaskSchedulerBottomSheet
import com.vny_bst.schedulerapp.ui.view.TaskSchedulerScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private val navViewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchedulerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainContentView(navViewModel, this)
                }
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun MainContentView(navViewModel: NavigationViewModel, activity: ComponentActivity) {
    TaskSchedulerBottomSheet(mainContent = { scope, bottomSheetState ->
        ScheduleContent(scope, bottomSheetState, navViewModel)
    }, sheetContent = {
        val selectedPosition = navViewModel.clickedPosition().observeAsState()
        InflateScreens(position = selectedPosition.value, activity)
    })
}

@Composable
fun InflateScreens(position: Int?, activity: ComponentActivity) {

    when (position) {

        0 -> {
            TaskSchedulerScreen()
        }

        1 -> {
            MusicSchedulerScreen(activity)
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
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontFamily = firaSansFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
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
                                viewModel.setClickedPosition(index)
                                bottomSheetState.show()
                            }
                        }
                    }
                )
            }
        }
    )
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SchedulerAppTheme(darkTheme = true) {
        val viewModel: NavigationViewModel = viewModel()
        MainContentView(viewModel, MainActivity())
    }
}