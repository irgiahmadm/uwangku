package org.d3ifcool.uwangku.ui.save


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import org.d3ifcool.uwangku.R
import org.d3ifcool.uwangku.adapter.RecyclerAdapterSave
import org.d3ifcool.uwangku.database.Save
import org.d3ifcool.uwangku.database.UwangkuDatabase
import org.d3ifcool.uwangku.databinding.FragmentSaveBinding
import org.d3ifcool.uwangku.ui.transaction.TransactionViewModel
import org.d3ifcool.uwangku.ui.transaction.TransactionViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class SaveFragment : Fragment() {

    private lateinit var viewModel: SaveViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = this.activity!!.application
        val dataSource = UwangkuDatabase.getInstance(application).transactionDAO

        val binding : FragmentSaveBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_save, container, false)


        binding.rvSave.setHasFixedSize(true)
        binding.rvSave.layoutManager = LinearLayoutManager(container!!.context)

        val viewModelFactory = SaveViewModelFactory(dataSource)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SaveViewModel::class.java)

        val adapter = RecyclerAdapterSave(container.context){
            //do intent
        }

        binding.fabAddSave.setOnClickListener {
            findNavController().navigate(R.id.action_saveFragment_to_insertSaveDataActivity)
        }

        binding.rvSave.adapter = adapter

        viewModel.getSaveDataList().observe(this, Observer<List<Save>>{list ->
            adapter.setListSave(list as ArrayList<Save>)
        })

        viewModel.getSizeData()?.observe(this,Observer<Int> {
            if(it > 0 && it != null){
                binding.imageViewEmptySave.visibility = View.GONE
                binding.textViewEmptyStateSave.visibility = View.GONE
            }
        })

        return binding.root
    }


}
