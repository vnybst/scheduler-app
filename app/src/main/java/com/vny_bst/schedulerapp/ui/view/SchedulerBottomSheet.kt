package com.vny_bst.schedulerapp.ui.view

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import com.vny_bst.schedulerapp.NavigationViewModel
import com.vny_bst.schedulerapp.ui.theme.roundBottomSheetShape
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Vinay Singh Bisht on 05-Oct-21.
 */

@ExperimentalMaterialApi
@Composable
fun TaskSchedulerBottomSheet(
    mainContent: @Composable (
        scope: CoroutineScope,
        bottomSheetState: ModalBottomSheetState,
    ) -> Unit,
    sheetContent: @Composable () -> Unit
) {

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetShape = roundBottomSheetShape(24),
        sheetElevation = 16.dp,
        sheetState = bottomSheetState,
        sheetContent = {
            sheetContent()
        }
    ) {
        mainContent(coroutineScope, bottomSheetState)
    }
}