package com.example.notesviewmodel.views.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesviewmodel.databinding.FragmentItemBinding
import com.example.notesviewmodel.views.db.EntitiyNote


class MyItemRecyclerViewAdapter(
    val context: Context,
    val onNotesCLickListainer: OnNotesCLickListainer
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {
    private var dataset = ArrayList<EntitiyNote>()


    inner class ViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tNote.setText(dataset[position].note)
        holder.binding.tTitolo.setText(dataset[position].titolo)

    }

    override fun getItemCount(): Int = dataset.size


    interface OnNotesCLickListainer {


    }

   public fun setDataList(dataset: ArrayList<EntitiyNote>) {
        this.dataset = dataset

    }


}