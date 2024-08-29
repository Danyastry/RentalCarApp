package com.example.rentalcarsapp.Screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.rounded.Bloodtype
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CarCrash
import androidx.compose.material.icons.rounded.Escalator
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShutterSpeed
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material.icons.rounded.StackedLineChart
import androidx.compose.material.icons.rounded.Timeline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentalcarsapp.Data.Car
import com.example.rentalcarsapp.R
import com.example.rentalcarsapp.VIewModel.VipCarsViewModel
import com.example.rentalcarsapp.ui.theme.Blur
import com.example.rentalcarsapp.ui.theme.Primary
import com.example.rentalcarsapp.ui.theme.poppins
import org.koin.androidx.compose.koinViewModel

@Composable
fun VipCarsDetailsScreen(
    navController: NavController,
    carId: String,
    viewModel: VipCarsViewModel = koinViewModel()
) {

    LaunchedEffect(carId) {
        viewModel.selectVipCarsById(carId)
    }

    val car by viewModel.selectedCar.collectAsState()
    var startAnimation by rememberSaveable { mutableStateOf(false) }
    val isSelected = rememberSaveable { mutableStateOf(false) }


    val imageOffsetX by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 300.dp,
        animationSpec = tween(durationMillis = 1500, easing = EaseInOut)
    )


    car?.let { car ->

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
                expandFrom = Alignment.Start,
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
                    .background(Primary)

            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBackIos,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .size(25.dp)
                        .offset(x = 10.dp, y = 23.dp)
                        .clickable(indication = null, interactionSource = remember {
                            MutableInteractionSource()
                        }, onClick = { navController.popBackStack() })
                )
                Column(modifier = Modifier.padding(top = 18.dp)) {
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(22.dp))
                        Text(
                            text = car.name,
                            fontFamily = poppins,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 12.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Box(modifier = Modifier.clip(RoundedCornerShape(5.dp))) {
                                Text(
                                    text = "Vip",
                                    color = Color.Black,
                                    fontFamily = poppins,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier
                                        .background(Color.White)
                                        .padding(horizontal = 12.dp)

                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Box(modifier = Modifier.clip(RoundedCornerShape(5.dp))) {
                                Text(
                                    text = "New",
                                    color = Color.Black,
                                    fontFamily = poppins,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier
                                        .background(Color.Green.copy(alpha = 0.4f))
                                        .padding(horizontal = 12.dp)

                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.38f)
                                .padding(vertical = 20.dp)
                                .clip(
                                    RoundedCornerShape(100.dp)
                                )
                                .background(Blur.copy(0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = car.image),
                                contentDescription = null,
                                modifier = Modifier.offset(x = imageOffsetX)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                            )
                            .background(Color.White.copy(0.8f))

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 12.dp)
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 9.dp)
                                    .size(width = 70.dp, height = 7.dp)
                                    .clip(RoundedCornerShape(22.dp))
                                    .background(Color.Gray)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Image(
                                    painter = painterResource(id = car.logo),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(45.dp)
                                        .offset(x = 10.dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .offset(x = (-10).dp)
                                        .size(42.dp)
                                        .clip(CircleShape)
                                        .background(Color.Black)
                                        .clickable(indication = null, interactionSource = remember {
                                            MutableInteractionSource()
                                        }, onClick = { isSelected.value = !isSelected.value }),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = if (isSelected.value) R.drawable.ic_like else R.drawable.ic_unlike),
                                        contentDescription = null, tint = Color.White,
                                        modifier = Modifier
                                            .size(20.dp)

                                    )
                                }
                            }
                            Text(
                                text = "${car.price}.00$ / 1 day",
                                fontSize = 22.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.Medium
                            )
                            VipDetailsSection(car = car)
                            Spacer(modifier = Modifier.height(22.dp))
                            Divider(thickness = 1.dp, color = Color.Black.copy(0.8f))
                            Spacer(modifier = Modifier.height(22.dp))
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(width = 300.dp, height = 45.dp)
                                        .clip(
                                            RoundedCornerShape(22.dp)
                                        )
                                        .background(Primary)
                                        .clickable { navController.navigate("bookCar") }
                                ) {
                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Rounded.Message,
                                            contentDescription = null,
                                            tint = Color.Black,
                                            modifier = Modifier.size(25.dp)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = "Book a car for 1 day",
                                            fontSize = 20.sp,
                                            fontFamily = poppins,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(50.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VipDetailsSection(car: Car) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        VipDetailRow(Icons.Rounded.CalendarMonth, "Year", car.year)

        Spacer(modifier = Modifier.height(12.dp))
        VipDetailRow(Icons.Rounded.Speed, "Top speed", car.topSpeed)

        Spacer(modifier = Modifier.height(12.dp))
        VipDetailRow(Icons.Rounded.Settings, "Horsepower", car.horsepower)

        Spacer(modifier = Modifier.height(12.dp))
        VipDetailRow(Icons.Rounded.StackedLineChart, "Num. of cylinders", car.cylinders)

        Spacer(modifier = Modifier.height(12.dp))
        VipDetailRow(Icons.Rounded.Bloodtype, "Fuel Type", car.fuelType)

        Spacer(modifier = Modifier.height(12.dp))
        VipDetailRow(Icons.Rounded.Timeline, "Max. load", car.maxLoad)

        Spacer(modifier = Modifier.height(12.dp))
        VipDetailRow(Icons.Rounded.CarCrash, "Engine", car.engine)

        Spacer(modifier = Modifier.height(12.dp))
        VipDetailRow(Icons.Rounded.Escalator, "Torque", car.torque)

        Spacer(modifier = Modifier.height(12.dp))
        VipDetailRow(Icons.Rounded.People, "Capacity", car.capacity)

        Spacer(modifier = Modifier.height(12.dp))
        VipDetailRow(Icons.Rounded.ShutterSpeed, "0-100 km/h", car.acceleration)
    }
}

@Composable
fun VipDetailRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
            )
        }
    }
}
