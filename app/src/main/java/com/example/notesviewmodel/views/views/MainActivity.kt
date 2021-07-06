package com.example.notesviewmodel.views.views

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentManager
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
    private lateinit var sheetBinding: LayoutSheetBinding
    private lateinit var viewModel: VIewModelNotes
    private lateinit var binding: ActivityMainBinding
    private lateinit var mFfragrmentManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()
        setInitDysplayInf()

        cercaByTitolo()

        hideView()
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

    private fun buttonFabInsert() {
        binding.fab.setOnClickListener {
            butonsheet()
        }
    }

    private fun cercaByTitolo() {

        val finderText = binding.cercaTitolo
        finderText.addTextChangedListener { x ->
            viewModel.aggiorna(x.toString())

        }
    }
    fun hideView() {

        val finderstatus = binding.cercaTitolo
        mFfragrmentManager = supportFragmentManager

        val homeFragment = ItemFragment()
        binding.bottomAppBar.setOnMenuItemClickListener {

            when (it.itemId) {
                R.id.menu_cercare -> {

                    if (finderstatus.isVisible) {
                        finderstatus.visibility = View.INVISIBLE
                    } else {
                        finderstatus.visibility = View.VISIBLE
                    }

                }

            }

            if (finderstatus.isVisible){
                binding.bottomAppBar.visibility=View.INVISIBLE
                if (finderstatus.isInvisible and binding.bottomAppBar.isVisible){
                    binding.bottomAppBar.visibility=View.VISIBLE
                    finderstatus.visibility=View.INVISIBLE
                }

                if (finderstatus.isVisible and binding.bottomAppBar.isInvisible){
                    binding.bottomAppBar.visibility=View.VISIBLE

                }

            }



            return@setOnMenuItemClickListener true


        }


    }


}