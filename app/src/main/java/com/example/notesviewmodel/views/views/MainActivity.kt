package com.example.notesviewmodel.views.views

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.example.notesviewmodel.R
import com.example.notesviewmodel.databinding.ActivityMainBinding
import com.example.notesviewmodel.databinding.LayoutSheetBinding
import com.example.notesviewmodel.views.db.EntitiyNote
import com.example.notesviewmodel.views.viewmodel.VIewModelNotes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var sheetBinding: LayoutSheetBinding
    lateinit var viewModel: VIewModelNotes
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setInitDysplayInf()


    }

    private fun setInitDysplayInf() {
        viewModel = ViewModelProvider(this).get(VIewModelNotes::class.java)
        buttonFabInsert()
    }

    suspend fun inserimento(title: EditText, note: EditText) {

        var titolo: String = sheetBinding.etTito.text.toString()
        var note: String = sheetBinding.etNota.text.toString()

        viewModel.insert(EntitiyNote(0, titolo, note))


    }
    private fun butonsheet() {
        sheetBinding = LayoutSheetBinding.inflate(layoutInflater)
        val btnInsert = MaterialDialog(this, BottomSheet()).show {
            cornerRadius(16f)
            customView(view = sheetBinding.root, scrollable = true)
            title(R.string.inserire)
            positiveButton {
                GlobalScope.launch {
                    inserimento(sheetBinding.etTito, sheetBinding.etNota)
                }
            }
            dismiss()

            negativeButton(R.string.cacellare) {

                dismiss()
            }

        }


    }

    fun buttonFabInsert() {
        binding.fab.setOnClickListener {
            butonsheet()
        }
    }
}