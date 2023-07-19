package com.example.sprintfinalmodulo6.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_detail")
data class PhonesDetailEntity(

    @PrimaryKey
    val id: String,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)