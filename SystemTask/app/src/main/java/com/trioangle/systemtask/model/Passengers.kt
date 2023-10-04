package com.trioangle.systemtask.model

data class Passengers(
    val `data`: List<Data>,
    val totalPages: Int,
    val totalPassengers: Int
)

data class Data(
    val __v: Int,
    val _id: String,
    val airline: List<Airline>,
    val name: String,
    val trips: Int
)

data class Airline(
    val country: String,
    val established: String,
    val head_quaters: String,
    val id: Int,
    val logo: String,
    val name: String,
    val slogan: String,
    val website: String
)