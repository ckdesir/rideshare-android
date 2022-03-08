package com.cornellappdev.scoop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

class Trips : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Composable
fun Title() {
    Row {
        ScoopIcon()
        Text(
            text = "Scoop",
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun BottomBar() {
    Row (
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.fillMaxWidth(1/3F),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home Icon"
            )
        }
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.fillMaxWidth(1/2F),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon"
            )
        }
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Profile Icon"
            )
        }
    }
}

@Preview
@Composable
fun MyApp() {
    Scaffold (
        scaffoldState = rememberScaffoldState(),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Ride Icon",
                modifier = Modifier.size(20.dp)
            )
        } },
        content = {
            Column (
                modifier = Modifier
                    .background(Color.White)
                    .padding(top = 20.dp)
                    .padding(horizontal = 10.dp)
            ) {
                Title()
                Spacer(modifier = Modifier.padding(20.dp))
                ActiveTrips()
                Spacer(modifier = Modifier.padding(10.dp))
                PendingTrips()
            }
        },
        bottomBar = { BottomAppBar(backgroundColor = Color.White)
        { BottomBar() }}
    )
}

@Composable
fun ScoopIcon() {
    Icon(
        imageVector = Icons.Filled.ShoppingCart,
        contentDescription = "Scoop Icon",
        modifier = Modifier.size(80.dp)
    )
}

@Composable
fun ActiveTrip() {
    Box (
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.LightGray, RoundedCornerShape(20.dp))
    )
}

@Composable
fun ActiveTrips() {
    Column () {
        Text(
            text = "Active trips"
        )
        LazyColumn () {
            item {
                ActiveTrip()
            }
            item {
                ActiveTrip()
            }
        }
    }
}

@Composable
fun PendingTrip() {
    Box (
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.LightGray, RoundedCornerShape(20.dp))
    )
}

@Composable
fun PendingTrips() {
    Column () {
        Text(
            text = "Pending trips"
        )
        LazyColumn () {
            item {
                PendingTrip()
            }
        }
    }
}