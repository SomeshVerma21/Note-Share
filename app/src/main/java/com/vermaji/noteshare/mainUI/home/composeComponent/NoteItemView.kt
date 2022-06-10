package com.vermaji.noteshare.mainUI.home.composeComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.vermaji.noteshare.R

@Preview
@Composable
fun HomeItem(){
    Row() {
        Image(painter = painterResource(id = R.drawable.noteshare_logos) , contentDescription = "note icon")

    }
}