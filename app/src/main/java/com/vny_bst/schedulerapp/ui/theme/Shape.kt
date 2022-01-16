package com.vny_bst.schedulerapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

fun roundBottomSheetShape(radius: Int) =
    RoundedCornerShape(radius.dp, radius.dp, 0.dp, 0.dp)