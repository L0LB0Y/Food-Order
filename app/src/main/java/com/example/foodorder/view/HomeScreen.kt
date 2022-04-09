package com.example.foodorder.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.foodorder.R
import com.example.foodorder.model.Meal
import com.example.foodorder.ui.theme.*
import com.example.foodorder.utilise.Common.makeMeSimple
import com.example.foodorder.viewmodel.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination

@Destination(route = "HomeScreen")
@Composable
@Preview(showBackground = true)
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    rememberSystemUiController().setSystemBarsColor(color = homeScreenStatusBarColo)
    BackGround {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val isLoading by remember {
                homeViewModel.isLoading
            }
            if (isLoading)
                Loading()
            TopBar()
            Categories(homeViewModel)
            SearchBar(homeViewModel)
            MealsList(homeViewModel)

        }
    }
}

@Composable
fun MealsList(homeViewModel: HomeViewModel) {
    val list by remember {
        homeViewModel.meals
    }
    LazyColumn(Modifier.fillMaxWidth()) {
        items(list) { item ->
            CardItem(item)
        }
    }
}

@Composable
fun TopBar() {
    Row(
        Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Food Order",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h4,
            color = Color.White
        )
        Image(
            painter = painterResource(id = R.drawable.ic_happy_man),
            contentDescription = "happy man",
            modifier = Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(imageBackground)
                .padding(5.dp)
        )
    }
}

@Composable
fun Categories(homeViewModel: HomeViewModel) {
    val list by remember {
        homeViewModel.category
    }
    var isSelected by remember {
        mutableStateOf(0)
    }
    LazyRow(
        Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
    ) {
        itemsIndexed(list) { index, item ->
            Text(
                text = item.strCategory ?: "No Data",
                style = MaterialTheme.typography.subtitle1,
                color = if (isSelected == index) Color.White else lightTextColor,
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .clickable {
                        isSelected = index
                        homeViewModel.filteredByCategory(item.strCategory ?: "No Data")
                    }
                    .background(color = if (isSelected == index) imageBackground else Color.Transparent)
                    .padding(horizontal = 15.dp, vertical = 7.dp)
            )
        }
    }
}

@Composable
fun SearchBar(homeViewModel: HomeViewModel) {
    var text by remember {
        homeViewModel.searchValue
    }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        TextField(
            value = text, onValueChange = { text = it },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .height(TextFieldDefaults.MinHeight)
                .shadow(elevation = 20.dp, shape = RoundedCornerShape(10.dp), clip = true),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "happy man", tint = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            },
            label = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart),
                        text = "Search",
                        color = Color.White,
                        style = MaterialTheme.typography.body2,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = imageBackground,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = homeScreenStatusBarColo,
                textColor = Color.White
            )
        )

    }


}

@Composable
fun BackGround(content: @Composable () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(homeScreenStatusBarColo)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .align(Alignment.BottomCenter)
                    .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(color = appStatusBarColor)
            )
        }
        content()
    }
}

@Composable
fun CardItem(meal: Meal) {
    Box(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 7.dp)
            .fillMaxWidth()
            .height(250.dp)
            .background(color = Color.Transparent)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color.White,
            elevation = 15.dp
        ) {
            Column(
                Modifier
                    .fillMaxSize()
            ) {
                Row(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .weight(0.3f)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${meal.strCategory}",
                        color = secondaryTextColor,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.weight(1f)
                    )
                    var isClicked by remember {
                        mutableStateOf(false)
                    }
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = " Like",
                        tint = Color.White,
                        modifier = Modifier
                            .size(40.dp)
                            .shadow(elevation = 10.dp, clip = true, shape = CircleShape)
                            .clip(CircleShape)
                            .clickable { isClicked = !isClicked }
                            .background(if (isClicked) imageBackground else iconBackgroundColor)
                            .padding(10.dp)
                    )
                }
                Column(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .weight(0.4f)
                        .fillMaxWidth(), verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${meal.strMeal?.makeMeSimple()}",
                        style = MaterialTheme.typography.h6,
                        color = textColor
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 3.dp),
                        text = "${meal.strIngredient1},${meal.strIngredient2}," +
                                "${meal.strIngredient3},${meal.strIngredient4}",
                        style = MaterialTheme.typography.caption,
                        color = Color.LightGray, fontFamily = FontFamily.Monospace,
                        maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    Modifier
                        .weight(0.2f)
                        .fillMaxWidth()
                        .background(gray)
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "location",
                        tint = Color.LightGray, modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "${meal.strArea}",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.weight(1f)
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = "happy man",
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Text(
                            text = "0",
                            color = secondaryTextColor,
                            style = MaterialTheme.typography.body2,
                            fontFamily = FontFamily.Monospace
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "happy man",
                            modifier = Modifier
                                .size(30.dp).background(Color.Transparent)
                                .shadow(
                                    elevation = 15.dp,
                                    clip = true
                                )
                        )
                    }
                }

            }
        }
        Image(
            painter = rememberImagePainter(
                data = "${meal.strMealThumb}",
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = "meal",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .shadow(elevation = 15.dp, clip = true, shape = CircleShape)
                .align(Alignment.TopCenter)
                .border(width = 5.dp, shape = CircleShape, color = Color.White)
                .clip(CircleShape)
        )
    }
}