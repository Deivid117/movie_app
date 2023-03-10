package com.dwh.movieapp.utils.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dwh.movieapp.R
import com.dwh.movieapp.ui.theme.DarkBlueCard
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun TopAppBar(
    showBackIcon: Boolean = false,
    navController: NavController = rememberNavController(),
    content: @Composable() () -> Unit = {}
){
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBarContent(
                showBackIcon,
                navigationIconClick ={
                    if(showBackIcon) {
                        coroutineScope.launch {
                            navController.popBackStack()
                        }
                    } else {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                }
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 25.dp, bottomEnd = 25.dp),
        drawerContent = {
            NavigationDrawer(scaffoldState, coroutineScope, navController)
        },
        content = {
            content()
        }
    )
}

@Composable
fun TopAppBarContent(
    showBackIcon: Boolean,
    navigationIconClick: () -> Unit
){
    TopAppBar(
        modifier = Modifier.clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)),
        backgroundColor = DarkBlueCard,
        elevation = 0.dp
    ) {
        if (showBackIcon) {
            Row(
                Modifier.fillMaxWidth().padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { navigationIconClick() },
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        } else {
            Row(
                Modifier.fillMaxWidth().padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navigationIconClick() },
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    }
}

