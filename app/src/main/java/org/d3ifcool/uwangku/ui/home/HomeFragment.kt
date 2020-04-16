package org.d3ifcool.uwangku.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3ifcool.uwangku.ui.transaction.TransactionViewModelFactory
import org.d3ifcool.uwangku.R
import org.d3ifcool.uwangku.database.UwangkuDatabase
import org.d3ifcool.uwangku.databinding.FragmentHomeBinding
import org.d3ifcool.uwangku.ui.transaction.TYPE_EXPENSE
import org.d3ifcool.uwangku.ui.transaction.TransactionViewModel

class HomeFragment : Fragment(){

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        binding.buttonToIncome.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_insertIncomeActivity)
        }
        binding.buttonToExpenses.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_insertExpenseActivity)
        }

        val application = this.activity!!.application
        val dataSource = UwangkuDatabase.getInstance(application).transactionDAO

        val viewModelFactory = HomeViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        viewModel.getIncome()?.observe(this, Observer<Int>{ income ->
            if(income != null) {
                binding.textViewIncomeValue.text = income.toString()
            }
        })

        viewModel.getExpense()?.observe(this, Observer<Int>{ expense ->
            if(expense != null) {
                binding.textViewExpenseValue.text = expense.toString()
            }

        })
        return binding.root
    }
}