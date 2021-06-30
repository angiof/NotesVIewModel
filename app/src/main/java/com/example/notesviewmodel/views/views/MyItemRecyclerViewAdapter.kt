package com.example.notesviewmodel.views.views

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.notesviewmodel.R
import com.example.notesviewmodel.databinding.FragmentItemBinding
import com.example.notesviewmodel.views.db.EntitiyNote
import com.example.notesviewmodel.views.models.Myapp
import com.example.notesviewmodel.views.viewmodel.VIewModelNotes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyItemRecyclerViewAdapter(val context: Context, val onNotesCLickListainer: OnNotesCLickListainer) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {
    lateinit var viewModel: VIewModelNotes
    private lateinit var popupMenu: PopupMenu
    private var dataset = ArrayList<EntitiyNote>()
    var wrapper: Context = ContextThemeWrapper(context, R.style.YOURSTYLE)

    inner class ViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }
    @RequiresApi(Build.VERSION_CODES.Q)
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
            pop(holder.binding.cardView)

        }

    }

    override fun getItemCount(): Int = dataset.size

    interface OnNotesCLickListainer {
        suspend fun onLonClick(item: EntitiyNote)
        fun click(item: EntitiyNote)

    }

    fun setDataList(dataset: ArrayList<EntitiyNote>) {
        this.dataset = dataset

    }
    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("RestrictedApi")
     fun pop(v: View) {

        val menuBuilder = MenuBuilder(context)
        val popupMenus = PopupMenu(wrapper, v)
        popupMenus.setForceShowIcon(true);
        popupMenus.inflate(R.menu.menu_pop)
        val optionsMenu = MenuPopupHelper(context, menuBuilder, v)
        optionsMenu.setForceShowIcon(true)


        popupMenus.setOnMenuItemClickListener {

            when(it.itemId){



            }

            return@setOnMenuItemClickListener true
        }

        popupMenus.gravity = Gravity.START
        popupMenus.show()

    }

}