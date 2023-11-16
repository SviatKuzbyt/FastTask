package com.sviatkuzbyt.fasttask.data.elements

data class UnDoneTask(
    val id: Long,
    var text: String,
    var position: Int
)

data class DoneTask(
    val id: Long,
    var text: String
)