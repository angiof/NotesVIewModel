package com.example.notesviewmodel.views.repo

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.notesviewmodel.views.db.DaoNotes
import com.example.notesviewmodel.views.db.DbNotes
import com.example.notesviewmodel.views.db.EntitiyNote

class Repositorio(context: Context) {

    //dao intanziata dal repo con tutte le sue query
    private var daoRepo: DaoNotes = DbNotes.getDatabase(context).notesDaos()

    //richiamo della query dalla var daoRepo che ritona un live data collegato alla qauery scelta
    var stampaTuttorepo: LiveData<List<EntitiyNote>> = daoRepo.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(entityNote: EntitiyNote) {
        daoRepo.insertNote(entityNote)

    }

    suspend fun CancellaNotesFromRepo(entityNote: EntitiyNote) {
        daoRepo.delateNotes(entityNote)

    }

}