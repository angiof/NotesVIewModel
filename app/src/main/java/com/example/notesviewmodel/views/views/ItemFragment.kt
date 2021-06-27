package com.example.notesviewmodel.views.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notesviewmodel.databinding.FragmentItemListBinding
import com.example.notesviewmodel.databinding.LayoutSheetBinding
import com.example.notesviewmodel.views.db.EntitiyNote
import com.example.notesviewmodel.views.models.snack
import com.example.notesviewmodel.views.viewmodel.VIewModelNotes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {
    lateinit var binding: FragmentItemListBinding
    lateinit var viewModel: VIewModelNotes
    lateinit var mAdapter: MyItemRecyclerViewAdapter
    lateinit var sheetBinding: LayoutSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.inflate(layoutInflater)
        setAdapterRecy()
        setViewModel()
        setAdapterRecy()

        return binding.root


    }

    private fun setAdapterRecy() {
        mAdapter = MyItemRecyclerViewAdapter(requireContext(), object :
            MyItemRecyclerViewAdapter.OnNotesCLickListainer {
        })
        with(binding.recy) {
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(VIewModelNotes::class.java)
        viewModel.notesFromViewModel.observe(viewLifecycleOwner) {
            mAdapter.setDataList(ArrayList(it))
            mAdapter.notifyDataSetChanged()
        }

        GlobalScope.launch {
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun snack() {
        activity?.window?.let { snack(it.decorView) }

    }

}


//}


