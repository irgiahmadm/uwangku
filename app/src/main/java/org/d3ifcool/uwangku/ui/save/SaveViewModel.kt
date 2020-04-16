package org.d3ifcool.uwangku.ui.save

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.d3ifcool.uwangku.database.Save
import org.d3ifcool.uwangku.database.TransactionDAO

class SaveViewModel(val database : TransactionDAO) : ViewModel(){
    private var viewModel = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModel)

    private val getSaveData = database.getAllSaveData()

    private val getSizeData = database.getSizeSaveData()

    override fun onCleared() {
        super.onCleared()
        viewModel.cancel()
    }

    fun getSaveDataList() = getSaveData

    fun getSizeData() = getSizeData

    fun insertSave(save: Save){
        uiScope.launch {
            insert(save)
        }
    }

    private suspend fun insert(save: Save){
        withContext(Dispatchers.IO) {
            database.insertSave(save)
        }
    }
}