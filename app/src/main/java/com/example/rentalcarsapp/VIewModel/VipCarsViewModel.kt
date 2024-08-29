package com.example.rentalcarsapp.VIewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rentalcarsapp.Data.Car
import com.example.rentalcarsapp.Data.vipCars
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VipCarsViewModel : ViewModel() {
    private val _vipCars: MutableStateFlow<List<Car>> = MutableStateFlow(vipCars)

    private val _selectedCar: MutableStateFlow<Car?> = MutableStateFlow(null)
    val selectedCar: StateFlow<Car?> = _selectedCar.asStateFlow()

    fun selectVipCarsById(carId: String) {
        viewModelScope.launch {
            val car = _vipCars.value.find { it.id == carId }
            _selectedCar.value = car
        }
    }
}