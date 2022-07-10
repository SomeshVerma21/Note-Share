package com.vermaji.noteshare.mainUI.home.composeComponent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.vermaji.noteshare.R

@Preview
@Composable
fun HomeItem(){
    Card {
        var expended by remember { mutableStateOf(false) }
        Column(Modifier.clickable { expended = !expended }) {
            Image(
                painter = painterResource(R.drawable.noteshare_logos),
                contentDescription = "NoteShare Logo",
            )
            AnimatedVisibility(visible = expended) {
                Text(
                    text = "Note Share",
                    style = MaterialTheme.typography.h2,
                )
            }
        }
    }
}