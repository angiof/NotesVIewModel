package com.example.notesviewmodel.views.views

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notesviewmodel.R
import com.example.notesviewmodel.databinding.FragmentItemBinding
import com.example.notesviewmodel.views.db.EntitiyNote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MyItemRecyclerViewAdapter(
    val context: Context,
    val onNotesCLickListainer: OnNotesCLickListainer
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {
    private lateinit var popupMenu: PopupMenu
    private var dataset = ArrayList<EntitiyNote>()


    inner class ViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tNote.setText(dataset[position].note)
        holder.binding.tTitolo.setText(dataset[position].titolo)
        holder.binding.cardView.setOnLongClickListener {
            Toast.makeText(context, "premuto", Toast.LENGTH_SHORT).show()

            GlobalScope.launch {

                onNotesCLickListainer.onLonClick(dataset[position])


            }
            return@setOnLongClickListener true
        }

        holder.binding.imBtn.setOnClickListener {

            pop(it)
        }

    }

    override fun getItemCount(): Int = dataset.size


    interface OnNotesCLickListainer {
        suspend fun onLonClick(item: EntitiyNote)
        fun click(item: EntitiyNote)
    }


    public fun setDataList(dataset: ArrayList<EntitiyNote>) {
        this.dataset = dataset

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("RestrictedApi")
    private fun pop(v: View) {
        val menuBuilder = MenuBuilder(context)
        val popupMenus = PopupMenu(context, v)
        popupMenus.setForceShowIcon(true);
        popupMenus.inflate(R.menu.menu_pop)
        val optionsMenu = MenuPopupHelper(context, menuBuilder, v)
        optionsMenu.setForceShowIcon(true)


        popupMenus.gravity=Gravity.START
        popupMenus.show()

    }


}