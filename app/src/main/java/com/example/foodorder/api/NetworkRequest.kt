package com.example.foodorder.api

import com.example.foodorder.model.Response
import com.example.foodorder.utilise.Constant.BASE_URL
import com.example.foodorder.utilise.Constant.FILTER_BY_CATEGORY
import com.example.foodorder.utilise.Constant.GET_CATEGORIES
import com.example.foodorder.utilise.Constant.GET_MEAL_BY_ID
import com.example.foodorder.utilise.ServerResponse
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class NetworkRequest @Inject constructor(private val httpClient: HttpClient) {
    suspend fun getListOfCategory(): ServerResponse<Response> {
        return try {
            val response =
                httpClient.get<Response>(urlString = "$BASE_URL$GET_CATEGORIES")
            ServerResponse.Success(data = response)
        } catch (ex: Exception) {
            ServerResponse.Error(error = "${ex.message}")
        }

    }


    suspend fun getListFilteredByCategory(category: String): ServerResponse<Response> {
        return try {
            val response =
                httpClient.get<Response>(urlString = "$BASE_URL$FILTER_BY_CATEGORY$category")
            ServerResponse.Success(data = response)
        } catch (ex: Exception) {
            ServerResponse.Error(error = "${ex.message}")
        }
    }

    suspend fun getMealByID(id: String): ServerResponse<Response> {
        return try {
            val response =
                httpClient.get<Response>(urlString = "$BASE_URL$GET_MEAL_BY_ID$id")
            ServerResponse.Success(data = response)
        } catch (ex: Exception) {
            ServerResponse.Error(error = "${ex.message}")
        }
    }

}