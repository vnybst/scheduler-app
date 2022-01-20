package com.vny_bst.schedulerapp.ui.history

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.vny_bst.schedulerapp.R

/**
 * Created by Vinay Singh Bisht on 20-Jan-22.
 */

@Preview
@Composable
fun History() {

    Scaffold(
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.history_label),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )

}