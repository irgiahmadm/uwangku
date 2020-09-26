package org.d3ifcool.uwangku.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import org.d3ifcool.uwangku.ui.transaction.TransactionViewModelFactory
import org.d3ifcool.uwangku.R
import org.d3ifcool.uwangku.database.UwangkuDatabase
import org.d3ifcool.uwangku.databinding.FragmentHomeBinding
import org.d3ifcool.uwangku.ui.transaction.TYPE_EXPENSE
import org.d3ifcool.uwangku.ui.transaction.TransactionViewModel
import org.d3ifcool.uwangku.util.StringFormat
import java.text.DecimalFormat
import kotlin.math.exp

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private  var income: Int = 0
    private var expense: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
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


        viewModel.income.observe(viewLifecycleOwner, Observer<Int> {
            if (it != null) {
                binding.textViewIncomeValue.text = StringFormat.intToCurrency(it)
            }
        })

        viewModel.expense.observe(viewLifecycleOwner, Observer<Int> {
            if (it != null) {
                binding.textViewExpenseValue.text = StringFormat.intToCurrency(it)
            }
        })

        viewModel.balance.observe(viewLifecycleOwner, Observer<Int> {
            if (it != null) {
                binding.textViewTotalBalance.text = "Total Balance " +StringFormat.intToCurrency(it)
            }
        })


        return binding.root
    }
}