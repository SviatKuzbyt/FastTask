package com.sviatkuzbyt.fasttask.data.elements

data class UnDoneTask(
    val id: Int,
    var text: String,
    var position: Int
)

data class DoneTask(
    val id: Int,
    var text: String
)