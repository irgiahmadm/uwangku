package org.d3ifcool.uwangku.ui.transaction

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.d3ifcool.uwangku.database.Transaction
import org.d3ifcool.uwangku.database.TransactionDAO

class TransactionViewModel(val database : TransactionDAO) : ViewModel() {

    private var viewModel = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModel)

    private val getTransaction = database.getAllData()

    override fun onCleared() {
        super.onCleared()
        viewModel.cancel()
    }

    fun getTransactionList() = getTransaction

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