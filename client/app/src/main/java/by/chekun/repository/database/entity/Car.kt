package by.chekun.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
        @PrimaryKey
        val id: Long
)
