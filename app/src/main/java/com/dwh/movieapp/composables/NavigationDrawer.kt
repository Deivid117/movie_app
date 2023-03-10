package com.dwh.movieapp.utils.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.dwh.domain.models.MenuItem
import com.dwh.movieapp.ui.theme.DarkBlueCard
import com.dwh.movieapp.R
import com.dwh.movieapp.navigation.Screens
import com.dwh.movieapp.ui.theme.DarkBlueBorder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    navController: NavController
){
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DrawerContent(scaffoldState, coroutineScope, navController)
    }
}

@Composable
fun DrawerContent(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    navController: NavController
){
    Column(Modifier .background(DarkBlueCard)) {
        DrawerHeader()
        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = Color.White, thickness = 3.dp)
        Spacer(modifier = Modifier.height(25.dp))
        DrawerBody(scaffoldState, coroutineScope, navController)
    }
}

@Composable
fun DrawerHeader(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(125.dp)
                .border(5.dp, DarkBlueBorder, CircleShape)
                .background(Color.White, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_movie_app),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(95.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        CustomText(text = "Movie App", fontSize = 30.sp)
    }
}

@Composable
fun DrawerBody(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    navController: NavController
) {
    val drawerBodyItems = listOf(
        MenuItem(
            title = stringResource(R.string.movies),
            icon = R.drawable.ic_movie,
            item1 = stringResource(R.string.popular_movies),
            item2 = stringResource(R.string.top_rated_movies),
            route1 = Screens.POPULAR_MOVIES_SCREEN,
            route2 = Screens.TOP_RATED_MOVIES_SCREEN,
        ),
        MenuItem(
            title = stringResource(R.string.series),
            icon = R.drawable.ic_series,
            item1 = stringResource(R.string.popular_series),
            item2 = stringResource(R.string.top_rated_series),
            route1 = Screens.POPULAR_SERIES_SCREEN,
            route2 = Screens.TOP_RATED_SERIES_SCREEN,
        )
    )

    Column(
        Modifier
            .fillMaxWidth()){
        LazyColumn(Modifier.padding(horizontal = 15.dp), verticalArrangement = Arrangement.spacedBy(35.dp)){
            items(drawerBodyItems){
                DrawerItem(
                    menuItem = it,
                    onClickRoute1 = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                            navController.navigate(it.route1) {
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                            }
                        }
                    },
                    onClickRoute2 = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                            it.route2?.let { it1 ->
                                navController.navigate(it1) {
                                    popUpTo(navController.graph.findStartDestination().id){
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun DrawerItem(
    menuItem: MenuItem,
    onClickRoute1: suspend() -> Unit = {},
    onClickRoute2: suspend() -> Unit = {}
){
    val navItemScope = rememberCoroutineScope()

    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = menuItem.icon),
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
            CustomText(text = menuItem.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(15.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 25.dp)) {
            CustomText(Modifier.clickable { navItemScope.launch { onClickRoute1() } }, text = menuItem.item1 ?: "", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))
            CustomText(Modifier.clickable { navItemScope.launch { onClickRoute2() } }, text = menuItem.item2 ?: "", fontSize = 18.sp)
        }
    }
}