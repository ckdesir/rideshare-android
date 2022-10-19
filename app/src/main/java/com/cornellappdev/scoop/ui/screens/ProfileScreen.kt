package com.cornellappdev.scoop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.cornellappdev.scoop.R
import com.cornellappdev.scoop.data.models.User

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
        Image(
            painter = rememberAsyncImagePainter(userState.value.profilePicUrl),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
        )
    }
}
