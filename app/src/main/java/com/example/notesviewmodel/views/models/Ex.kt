package com.example.notesviewmodel.views.models

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun snack(view: View){

    val snacki: Unit = Snackbar.make(view,"ddd",Snackbar.LENGTH_LONG).show()


}