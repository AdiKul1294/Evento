package com.devlite.evento.dataclasses

data class Event(
    val title: String,
    val location: String,
    val desc: String,
    val time: String,
    val isHeader: Boolean
)
