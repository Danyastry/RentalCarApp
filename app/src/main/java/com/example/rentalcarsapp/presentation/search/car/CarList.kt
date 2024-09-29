package com.example.rentalcarsapp.presentation.search.car

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rentalcarsapp.data.Car

@Composable
fun CarList(
    cars: List<Car>,
    carType: String,
    searchQuery: String,
    navController: NavController
) {
    val filteredCars = remember(searchQuery) {
        cars.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(
            top = 22.dp,
            bottom = 80.dp
        )
    ) {
        itemsIndexed(filteredCars) { index, car ->
            CarItems(
                car = car, modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clickable(indication = null, interactionSource = remember {
                        MutableInteractionSource()
                    }, onClick = { navController.navigate("carDetails/$carType/${car.id}") })
            )
            Spacer(modifier = Modifier.height(22.dp))
        }
    }
}