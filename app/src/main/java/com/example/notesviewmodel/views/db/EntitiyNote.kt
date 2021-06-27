package com.example.notesviewmodel.views.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class EntitiyNote(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "titolo") val titolo: String,
    @ColumnInfo(name = "note") val note: String
)