package com.example.foodorder.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.annotation.Destination

@Destination(route = "FavoritesScreen")
@Composable
fun FavoritesScreen() {
    Column(Modifier.fillMaxSize()) {
        Box(Modifier.weight(0.5f).fillMaxSize().background(Color.Black)) {
            
        }
        Box(Modifier.weight(0.5f).fillMaxSize().background(Color.Cyan)) {

        }
        Box(Modifier.weight(0.1f).fillMaxSize().background(Color.Yellow)) {

        }
    }
}