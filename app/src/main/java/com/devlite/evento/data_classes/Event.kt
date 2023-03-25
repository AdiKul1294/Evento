package com.devlite.evento.data_classes

data class Event(
    val eid: String,
    val title: String,
    val location: String,
    val desc: String,
    val time: String,
    val isHeader: Boolean
)
