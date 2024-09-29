package com.example.rentalcarsapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CarSearchViewModel : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}