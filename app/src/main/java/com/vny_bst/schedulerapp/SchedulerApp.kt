package com.vny_bst.schedulerapp

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vny_bst.schedulerapp.ui.history.History
import com.vny_bst.schedulerapp.ui.home.Home
import com.vny_bst.schedulerapp.ui.settings.Settings
import com.vny_bst.schedulerapp.ui.theme.firaSansFamily
import com.vny_bst.schedulerapp.viewmodel.TaskScheduleViewModel
import java.util.*

/**
 * Created by Vinay Singh Bisht on 19-Jan-22.
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SchedulerApp(activity: Activity? = null) {
    val navController = rememberNavController()
    val items = listOf(Screens.Home, Screens.History, Screens.Settings)
    var currentNavigationItem by remember { mutableStateOf("Home") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = currentNavigationItem.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                        },
                        fontFamily = firaSansFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        bottomBar = {
            BottomNavigation(
                contentColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screens ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screens.icon),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(stringResource(screens.label))
                        },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screens.route
                        } == true, onClick = {
                            navController.navigate(screens.route) {
                                currentNavigationItem = screens.route
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Gray
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = Screens.Home.route,
            Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.route) {
                if (activity != null) {
                    Home(activity)
                }
            }
            composable(Screens.History.route) {
                if (activity != null) {
                    History(taskScheduleViewModel = TaskScheduleViewModel(activity.application))
                }
            }
            composable(Screens.Settings.route) {
                Settings()
            }

        }
    }
}