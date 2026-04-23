package com.idat.appyepezjorge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblyepez")
data class Mascota(
    @PrimaryKey
    val codigo: String,
    val nombre: String,
    val tipo: String,
    val edad: Int
)