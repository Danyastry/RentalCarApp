package com.example.rentalcarsapp.Details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentalcarsapp.ui.theme.Blur

@Composable
fun TabLayout(
    modifier: Modifier,
    isSelectedIndex: Int = 0,
    items: List<Pair<String, @Composable () -> Unit>>,
    textHeight: Dp = 35.dp,
    color: Color,
    fontFamily: FontFamily,
    onTapClick: (Int) -> Unit,
    indicatorPadding: PaddingValues = PaddingValues()
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            items.forEachIndexed { index, pair ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(textHeight)
                        .weight(1f)
                        .clickable(indication = null, interactionSource = remember {
                            MutableInteractionSource()
                        }, onClick = { onTapClick(index) }),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(modifier = Modifier.fillMaxWidth(0.5f)) {
                        Text(
                            text = pair.first,
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                            fontWeight = if (isSelectedIndex == index) FontWeight.ExtraBold else FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            color = color.copy(if (isSelectedIndex == index) 1f else .5f),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    AnimatedVisibility(
                        visible = isSelectedIndex == index,
                        enter = scaleIn(),
                        exit = scaleOut()
                    ) {
                        if (isSelectedIndex == index)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(CircleShape)
                                    .height(3.dp)
                                    .background(Color.White)
                                    .padding(indicatorPadding)
                                    .background(Blur.copy(0.7f))
                            )
                    }
                }
            }
        }
        AnimatedContent(targetState = isSelectedIndex) {
            items[it].second()
        }
    }
}