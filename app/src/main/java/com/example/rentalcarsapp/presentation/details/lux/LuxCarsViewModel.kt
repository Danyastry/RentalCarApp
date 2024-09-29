package com.example.rentalcarsapp.presentation.details.lux

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentalcarsapp.data.Car
import com.example.rentalcarsapp.data.luxCars
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LuxCarsViewModel : ViewModel() {
    private val _luxCars: MutableStateFlow<List<Car>> = MutableStateFlow(luxCars)

    private val _selectedCar: MutableStateFlow<Car?> = MutableStateFlow(null)
    val selectedCar: StateFlow<Car?> = _selectedCar.asStateFlow()

    fun selectLuxCarById(carId: String) {
        viewModelScope.launch {
            val car = _luxCars.value.find { it.id == carId }
            _selectedCar.value = car
        }
    }
}