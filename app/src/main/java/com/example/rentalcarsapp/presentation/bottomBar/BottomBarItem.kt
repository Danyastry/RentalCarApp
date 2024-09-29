package com.example.rentalcarsapp.presentation.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Analytics
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem(
    val icon: ImageVector,
    val selected: Boolean
)

val bottomBarItem = listOf(
    BottomBarItem(
        icon = Icons.Rounded.Home,
        selected = true
    ),
    BottomBarItem(
        icon = Icons.Rounded.AccountBox,
        selected = false
    ),
    BottomBarItem(
        icon = Icons.Rounded.Analytics,
        selected = false
    ),
    BottomBarItem(
        icon = Icons.Rounded.Settings,
        selected = false
    )
)