package com.example.notesviewmodel.views.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.notesviewmodel.views.db.EntitiyNote
import com.example.notesviewmodel.views.repo.Repositorio
import kotlinx.coroutines.launch

class VIewModelNotes(application: Application) : AndroidViewModel(application) {

    private var aggiorna=MutableLiveData("")

    private val repositorioFromViewModel: Repositorio = Repositorio(application)



    val frasiTrack:LiveData<List<EntitiyNote>> = Transformations.switchMap(aggiorna){ string->
        repositorioFromViewModel.TrackFrasi(string)

    }

    suspend fun insert(entitiyNote: EntitiyNote) = viewModelScope.launch {
        repositorioFromViewModel.insert(entityNote = entitiyNote)
    }

    suspend fun cancellaNotesFromViewModel(entitiyNote: EntitiyNote) {
        repositorioFromViewModel.CancellaNotesFromRepo(entitiyNote)
    }


    fun aggiorna(find: String) {
        aggiorna.value=find
    }

}