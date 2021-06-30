package com.example.notesviewmodel.views.models

import android.app.Application

class Myapp :Application() {

    companion object{
        //instance del context
        lateinit var instance:Myapp
    }

    override fun onCreate() {
        super.onCreate()
        //instance dal on create app
        instance=this
    }
}