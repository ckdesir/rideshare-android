package com.cornellappdev.scoop.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cornellappdev.scoop.ScoopScreen

@Composable
fun ScoopTabRow(
    allScreens: List<ScoopScreen>,
    onTabSelected: (ScoopScreen) -> Unit,
    currentScreen: ScoopScreen
) {
    TabRow(
        selectedTabIndex = allScreens.indexOf(currentScreen),
        modifier = Modifier
            .selectableGroup()
            .fillMaxWidth(),
        backgroundColor = Color.White
    ) {
        allScreens.forEachIndexed { _, scoopScreen ->
            val color = MaterialTheme.colors.onSurface
            val selected = currentScreen == scoopScreen
            val durationMillis =
                if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
            val animSpec = remember {
                tween<Color>(
                    durationMillis = durationMillis,
                    easing = LinearEasing,
                    delayMillis = TabFadeInAnimationDelay
                )
            }
            val tabTintColor by animateColorAsState(
                targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
                animationSpec = animSpec
            )
            Tab(
                icon = {
                    Icon(
                        painter = painterResource(id = scoopScreen.icon),
                        contentDescription = scoopScreen.name,
                        tint = tabTintColor
                    )
                },
                selected = currentScreen == scoopScreen,
                onClick = { onTabSelected(scoopScreen) },
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100