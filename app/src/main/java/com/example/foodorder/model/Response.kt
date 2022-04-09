package com.example.foodorder.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val meals: List<Meal>
)