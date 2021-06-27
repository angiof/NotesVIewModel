package com.example.notesviewmodel.views.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesviewmodel.views.db.EntitiyNote
import com.example.notesviewmodel.views.repo.Repositorio
import kotlinx.coroutines.launch

class VIewModelNotes(application: Application) : AndroidViewModel(application) {

    private val repositorioFromViewModel: Repositorio = Repositorio(application)

    val notesFromViewModel: LiveData<List<EntitiyNote>> = repositorioFromViewModel.stampaTuttorepo


  suspend  fun insert(entitiyNote: EntitiyNote) = viewModelScope.launch {

        repositorioFromViewModel.insert(entityNote = entitiyNote)

    }


}