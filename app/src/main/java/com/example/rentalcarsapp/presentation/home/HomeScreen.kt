package com.example.rentalcarsapp.presentation.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentalcarsapp.R
import com.example.rentalcarsapp.ui.theme.DarkGray
import com.example.rentalcarsapp.ui.theme.poppins

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun HomeScreen(
    navController: NavController
) {

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val textOffsetY by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else (-200).dp,
        animationSpec = tween(
            durationMillis = 1500,
            easing = EaseInOut
        )
    )

    val imageOffsetX by animateDpAsState(
        targetValue = if (startAnimation) 60.dp else 300.dp,
        animationSpec = tween(durationMillis = 1500, easing = EaseInOut)
    )

    LaunchedEffect(Unit) {
        startAnimation = true

    }

    AnimatedVisibility(
        visible = startAnimation,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 1000,
                easing = EaseInOut
            )
        ) + expandHorizontally(
            expandFrom = Alignment.CenterHorizontally,
            animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
        ) + fadeIn(
            tween(
                durationMillis = 1000,
                easing = EaseInOut
            )
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
        ) + shrinkHorizontally(
            tween(durationMillis = 1000, easing = EaseInOut)
        ) + fadeOut(animationSpec = tween(1000, easing = EaseInOut))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Lux",
                    fontSize = 220.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = poppins,
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = listOf(Color.Black, Color.White),
                            tileMode = TileMode.Mirror
                        )
                    ),
                    modifier = Modifier
                        .offset(x = textOffsetY)
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(18.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 3.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.mustang_car),
                contentDescription = null,
                modifier = Modifier
                    .scale(1.2f)
                    .offset(x = imageOffsetX, y = (-60).dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp)
                    .offset(y = 185.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Your Ultimate",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppins,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Car Rental",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = poppins,
                        color = DarkGray,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 3.dp)
                    )
                }
                Text(
                    text = "Experience",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppins
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Lorem ipsum may be used as a placeholder",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black.copy(0.6f),
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = "before final copy is available",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black.copy(0.6f),
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Spacer(modifier = Modifier.height(80.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 120.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = { navController.navigate("auth") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(
                        text = "Let's Get Started",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = poppins,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color.White.copy(0.6f),
                        modifier = Modifier.offset(x = 35.dp)
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color.White.copy(0.8f),
                        modifier = Modifier.offset(x = 25.dp)
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.offset(x = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Already have an account?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black.copy(0.7f),
                        fontFamily = poppins
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Sign In",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = poppins,
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 12.dp)
                            .padding(top = 2.dp)
                            .clickable(
                                indication = null,
                                interactionSource = MutableInteractionSource(),
                                onClick = {
                                    navController.navigate("auth")
                                }
                            )
                    )
                }
            }
        }
    }
}

