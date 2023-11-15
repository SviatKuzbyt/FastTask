package com.sviatkuzbyt.fasttask.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var text: String,
    var position: Int,
    var isDone: Boolean = false
)