package com.jiahan.fave.database

import androidx.room.Entity

@Entity
data class sqlite_sequence constructor(
    val name: String
)