package org.d3ifcool.uwangku.ui.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import org.d3ifcool.uwangku.R
import org.d3ifcool.uwangku.adapter.RecyclerAdapterTransaction
import org.d3ifcool.uwangku.database.Transaction
import org.d3ifcool.uwangku.database.UwangkuDatabase
import org.d3ifcool.uwangku.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {

    private lateinit var viewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentTransactionBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_transaction, container, false)

        val application = this.activity!!.application
        val dataSource = UwangkuDatabase.getInstance(application).transactionDAO

        val viewModelFactory =
            TransactionViewModelFactory(dataSource)

        viewModel = ViewModelProvider(this, viewModelFactory).get(TransactionViewModel::class.java)


        binding.rvTransaction.setHasFixedSize(true)
        binding.rvTransaction.layoutManager = LinearLayoutManager(container!!.context)

        val adapter = RecyclerAdapterTransaction(container.context){

        }

        binding.rvTransaction.adapter = adapter

        viewModel.getTransactionList().observe(viewLifecycleOwner, Observer<List<Transaction>>{ list ->
            adapter.setListTransaction(list as ArrayList<Transaction>)
        })

        return binding.root
    }

}