package com.example.foodorder.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.foodorder.ui.theme.homeScreenStatusBarColo
import com.example.foodorder.ui.theme.secondaryImageBackground
import com.example.foodorder.view.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.rememberNavHostEngine

@Destination(start = true)
@Composable
fun ScreensHolder() {
    val engine = rememberNavHostEngine()
    val navGraph = engine.rememberNavController()
    val scaffoldState = rememberScaffoldState()
    Scaffold(Modifier.fillMaxSize(), scaffoldState = scaffoldState, bottomBar = {
        BottomBar {
            when (it) {
                1 -> navGraph.navigate("HomeScreen")
                2 -> navGraph.navigate("FavoritesScreen")
                3 -> navGraph.navigate("CartScreen")
            }
        }
    }, content = {
        DestinationsNavHost(modifier = Modifier
            .fillMaxWidth().padding(it),
            navGraph = NavGraphs.root,
            startRoute = HomeScreenDestination,
            engine = engine, navController = navGraph
        )
    })


}

@Composable
fun BottomBar(onClick: (pageNumber: Int) -> Unit) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        elevation = 20.dp,
        contentPadding = PaddingValues(10.dp),
        backgroundColor = Color.White
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            var currentSelected by remember {
                mutableStateOf(1)
            }
            IconButton(modifier = Modifier.weight(1f), onClick = {
                currentSelected = 1
                onClick(1)
            }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val color =
                        if (currentSelected == 1) homeScreenStatusBarColo else secondaryImageBackground
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "",
                        modifier = Modifier.size(35.dp),
                        tint = color
                    )
                    AnimatedVisibility(visible = (currentSelected == 1)) {
                        Text(
                            text = "Home",
                            color = color,
                            style = MaterialTheme.typography.caption,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
            IconButton(modifier = Modifier.weight(1f), onClick = {
                currentSelected = 2
                onClick(2)
            }) {
                val color =
                    if (currentSelected == 2) homeScreenStatusBarColo else secondaryImageBackground
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "",
                        modifier = Modifier.size(35.dp),
                        tint = color
                    )
                    AnimatedVisibility(visible = (currentSelected == 2)) {
                        Text(
                            text = "Favorite",
                            color = color,
                            style = MaterialTheme.typography.caption,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
            IconButton(modifier = Modifier.weight(1f), onClick = {
                currentSelected = 3
                onClick(3)
            }) {
                val color =
                    if (currentSelected == 3) homeScreenStatusBarColo else secondaryImageBackground
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "",
                        modifier = Modifier.size(35.dp),
                        tint = color
                    )
                    AnimatedVisibility(visible = (currentSelected == 3)) {
                        Text(
                            text = "Cart",
                            color = color,
                            style = MaterialTheme.typography.caption,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}
