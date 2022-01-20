package com.vny_bst.schedulerapp

/**
 * Created by Vinay Singh Bisht on 19-Jan-22.
 */

sealed class Screens(val route: String, val label: Int, val icon: Int) {
    object Home : Screens("home", R.string.home_label, R.drawable.ic_outline_home_24)
    object History :
        Screens("history", R.string.history_label, R.drawable.ic_outline_history_24)

    object Settings :
        Screens("settings", R.string.settings_label, R.drawable.ic_outline_settings_24)
}