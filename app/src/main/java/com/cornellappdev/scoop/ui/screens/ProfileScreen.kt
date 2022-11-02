package com.cornellappdev.scoop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.User
import com.cornellappdev.scoop.ui.components.profile.ProfileCard
import com.cornellappdev.scoop.ui.theme.Green

@Composable
@Preview
fun ProfileScreen() {
    val userState = remember {
        mutableStateOf(
            User()
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_profile_background),
            contentDescription = stringResource(R.string.profile_background),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Column {
            Spacer(
                modifier = Modifier
                    .height(133.dp)
                    .fillMaxWidth()
            )
            ProfileCard(userState)
        }
        Image(
            // painter = rememberAsyncImagePainter(userState.value.profilePicUrl),
            painter = painterResource(R.drawable.ic_profile_placeholder),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 68.dp)
                .size(128.dp)
                .clip(CircleShape)
                .align(Alignment.TopCenter)
                .border(width = 3.dp, color = Green, shape = CircleShape)
        )
    }
}
