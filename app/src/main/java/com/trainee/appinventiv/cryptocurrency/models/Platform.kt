package com.trainee.appinventiv.cryptocurrency.models

data class Platform(
    val id: Int,
    val name: String,
    val slug: String,
    val symbol: String,
    val token_address: String
)