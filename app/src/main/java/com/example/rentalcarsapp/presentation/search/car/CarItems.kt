package com.example.rentalcarsapp.presentation.search.car

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentalcarsapp.data.Car
import com.example.rentalcarsapp.R
import com.example.rentalcarsapp.ui.theme.DarkGray

@Composable
fun CarItems(modifier: Modifier = Modifier, car: Car) {
    val isSelected = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(car.bgColor)
    ) {
        Box(
            modifier = Modifier
                .offset(x = (320).dp, y = 8.dp)
                .size(35.dp)
                .clip(CircleShape)
                .background(DarkGray)
                .clickable(indication = null, interactionSource = remember {
                    MutableInteractionSource()
                }, onClick = { isSelected.value = !isSelected.value }),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = if (isSelected.value) R.drawable.ic_like else R.drawable.ic_unlike),
                contentDescription = null, tint = Color.White,
                modifier = Modifier
                    .size(18.dp)

            )
        }
        Image(
            painter = painterResource(id = car.image),
            contentDescription = null, modifier = Modifier.offset(x = 160.dp, y = 20.dp)
        )
        Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
            Column {
                CarInfo(car = car)
                Spacer(modifier = Modifier.height(15.dp))
                CarsRating(car = car)
            }
            BuyButton(car = car)
        }
    }
}

@Composable
fun BuyButton(modifier: Modifier = Modifier, car: Car) {
    Box(modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(40.dp))
                .background(DarkGray)
                .padding(vertical = 8.dp)
                .padding(start = 20.dp, end = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "${car.rentalDays} rental days",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "${car.price}.00 $",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(0.8f),
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
fun CarInfo(modifier: Modifier = Modifier, car: Car) {
    Row(
        modifier = Modifier.padding(top = 20.dp, start = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(car.logo),
            contentDescription = null,
            modifier = modifier
                .clip(RoundedCornerShape(100.dp))
                .background(Color.White)
                .padding(6.dp)
                .size(35.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Color:", fontSize = 12.sp, color = Color.Black.copy(0.8f))
                Spacer(modifier = Modifier.width(3.dp))
                Box(
                    modifier = modifier
                        .clip(CircleShape)
                        .size(15.dp)
                        .background(car.color)
                        .border(color = Color.Black, width = 1.dp, shape = CircleShape)
                )
            }
            Text(text = car.name, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CarsRating(modifier: Modifier = Modifier, car: Car) {
    Column(modifier = Modifier.padding(start = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box {
                Rater(image = car.recommenders[0])
                Rater(image = car.recommenders[1], modifier = modifier.padding(start = 24.dp))
                Rater(image = car.recommenders[2], modifier = modifier.padding(start = 48.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = car.recommendationRate.toString(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${car.recommendation}% Recommended",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun Rater(modifier: Modifier = Modifier, image: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier = modifier
            .size(30.dp)
            .clip(CircleShape)
            .border(color = Color.Black, width = 2.dp, shape = CircleShape)
    )
}




