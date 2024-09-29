package com.example.rentalcarsapp.presentation.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rentalcarsapp.ui.theme.Blur

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    bottomBarItems: List<BottomBarItem>,
    onItemSelected: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(26.dp))
            .background(Blur)
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        bottomBarItems.forEachIndexed { index, bottomBarItem ->
            if (bottomBarItem.selected) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clip(RoundedCornerShape(14.dp))
                        .background(Blur),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = bottomBarItem.icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                indication = null, interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                onClick = { onItemSelected(index) }
                            )
                    )
                }
            } else {
                Icon(
                    imageVector = bottomBarItem.icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable(
                            indication = null, interactionSource = remember {
                                MutableInteractionSource()
                            },
                            onClick = { onItemSelected(index) }
                        )
                )
            }
        }
    }
}
