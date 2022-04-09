package com.example.foodorder.repository

import com.example.foodorder.api.NetworkRequest
import javax.inject.Inject

class HomeRepository @Inject constructor(private val networkRequest: NetworkRequest) {

    suspend fun getListOfCategory() = networkRequest.getListOfCategory()

    suspend fun filterByCategory(category: String) =
        networkRequest.getListFilteredByCategory(category)

    suspend fun getMealByID(id: String) = networkRequest.getMealByID(id)
}