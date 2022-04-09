package com.example.foodorder.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodorder.model.Meal
import com.example.foodorder.repository.HomeRepository
import com.example.foodorder.utilise.Common
import com.example.foodorder.utilise.ServerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val homeRepository: HomeRepository,
    private val context: Context
) : ViewModel() {
    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private var _category = mutableStateOf<List<Meal>>(emptyList())
    val category: State<List<Meal>> = _category

    private var _meals = mutableStateOf<List<Meal>>(emptyList())
    val meals: State<List<Meal>> = _meals

    var searchValue = mutableStateOf("")

    init {
        getTheDataFromAPI()
    }

    private fun getTheDataFromAPI() {
        viewModelScope.launch {
            _isLoading.value = true
            var firstCategory = ""
            getCategory {
                firstCategory = it.first().strCategory ?: "No Data"
                _category.value = it
            }
            getListOfMealByCategory(firstCategory) {
                if (it.isNotEmpty())
                    getTheMealWithFullData(it.take(7))
            }
        }
    }

    private fun getTheMealWithFullData(list: List<Meal>) {
        viewModelScope.launch {
            val listOfMealWithoutData = mutableListOf<Meal>()
            list.forEach { meal ->
                when (val response = homeRepository.getMealByID(id = meal.idMeal ?: "No Data")) {
                    is ServerResponse.Success -> {
                        response.data?.meals?.let { listOfMealWithoutData.add(it.first()) }
                    }
                    else -> {

                    }
                }
            }
            _meals.value = listOfMealWithoutData
            _isLoading.value = false
        }
    }

    private suspend fun getListOfMealByCategory(
        category: String,
        onDataFetched: (list: List<Meal>) -> Unit
    ) {
        when (val response = homeRepository.filterByCategory(category)) {
            is ServerResponse.Success -> {
                response.data?.meals?.let { onDataFetched(it) }
            }
            is ServerResponse.Error -> {
                Common.mackToast(context = context, message = "Server Error Pleas Try Again")
                onDataFetched(emptyList())
            }
        }
    }

    private suspend fun getCategory(onDataFetched: (list: List<Meal>) -> Unit) {
        when (val response = homeRepository.getListOfCategory()) {
            is ServerResponse.Success -> {
                response.data?.meals?.let { onDataFetched(it) }
            }
            is ServerResponse.Error -> {
                Common.mackToast(context = context, message = "Server Error Pleas Try Again")
                onDataFetched(emptyList())
            }
        }
    }

    fun filteredByCategory(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            getListOfMealByCategory(category) {
                if (it.isNotEmpty())
                    getTheMealWithFullData(it.take(7))
            }
            _isLoading.value = false
        }

    }
}