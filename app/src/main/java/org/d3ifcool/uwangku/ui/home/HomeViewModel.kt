package org.d3ifcool.uwangku.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.d3ifcool.uwangku.database.Transaction
import org.d3ifcool.uwangku.database.TransactionDAO
import org.d3ifcool.uwangku.ui.transaction.TYPE_EXPENSE
import org.d3ifcool.uwangku.ui.transaction.TYPE_INCOME

class HomeViewModel(val database : TransactionDAO) : ViewModel(){


    private var viewModel = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModel)

    private val getTransaction = database.getAllData()

    val typeIncome = TYPE_INCOME
    val typeExpense = TYPE_EXPENSE

    fun getIncome() = database.getTotalByType(typeIncome)
    fun getExpense() = database.getTotalByType(typeExpense)

    fun insertTransactions(transaction: Transaction){
        uiScope.launch {
            insert(transaction)
        }
    }

    suspend fun insert(transaction: Transaction){
        withContext(Dispatchers.IO){
            database.insertTransaksi(transaction)
        }
    }

}