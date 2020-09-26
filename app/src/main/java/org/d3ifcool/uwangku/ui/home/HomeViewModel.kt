package org.d3ifcool.uwangku.ui.home

import android.util.Log
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

    val typeIncome = TYPE_INCOME
    val typeExpense = TYPE_EXPENSE


    /*fun getIncome() = database.getTotalByType(typeIncome)
    fun getExpense() = database.getTotalByType(typeExpense)

    fun getBalance() =  - getExpense() as MutableLiveData*/

    private var _income = MutableLiveData<Int>()
    val income : LiveData<Int>
        get() = _income

    private var _expense = MutableLiveData<Int>()
    val expense : LiveData<Int>
        get() = _expense

    private var _balance = MutableLiveData<Int>()
    val balance : LiveData<Int>
        get() = _balance


    init{
        uiScope.launch {
            _income.value = getDataIncome()
            _expense.value = getDataExpense()
            _balance.value = _income.value!!.minus(_expense.value!!)
            Log.d("INCOME ", balance.value.toString())
        }
    }

    private suspend fun getDataIncome() = withContext(Dispatchers.IO){
        database.getTotalByType(typeIncome)
    }
    private suspend fun getDataExpense() = withContext(Dispatchers.IO){
        database.getTotalByType(typeExpense)
    }
}