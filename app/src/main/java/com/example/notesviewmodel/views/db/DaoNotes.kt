package com.example.notesviewmodel.views.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoNotes {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(entitiyNote: EntitiyNote)

    @Delete()
    fun delateNotes(entitiyNote: EntitiyNote)

    @Update
    fun aggiornaNotes(entitiyNote: EntitiyNote)

    @Query("SELECT * FROM notes order by titolo asc ")
    fun getAll(): LiveData<List<EntitiyNote>>


}