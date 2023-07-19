package com.example.sprintfinalmodulo6.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_list")
data class PhonesEntity (
        @PrimaryKey
        val id: String,
        val name: String,
        val price: Int,
        val image: String
        )