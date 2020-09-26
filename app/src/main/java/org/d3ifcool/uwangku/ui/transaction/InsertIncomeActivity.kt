package org.d3ifcool.uwangku.ui.transaction

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import org.d3ifcool.uwangku.MainActivity
import org.d3ifcool.uwangku.R
import org.d3ifcool.uwangku.database.Transaction
import org.d3ifcool.uwangku.database.UwangkuDatabase
import org.d3ifcool.uwangku.databinding.ActivityInsertIncomeBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

const val TYPE_INCOME = "type_income"
class InsertIncomeActivity : AppCompatActivity() {

    private lateinit var viewModel: TransactionViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityInsertIncomeBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_insert_income)

        val application = this.application
        val dataSource = UwangkuDatabase.getInstance(application).transactionDAO

        val viewModelFactory =
            TransactionViewModelFactory(dataSource)

        viewModel = ViewModelProvider(this, viewModelFactory).get(TransactionViewModel::class.java)

        binding.buttonAddIncome.setOnClickListener {

            when {
                binding.editTextWriteMemoIncome.text.isEmpty() -> Toast.makeText(this,R.string.error_insert,
                    Toast.LENGTH_SHORT).show()
                binding.editTextAmountIncome.text.isEmpty() -> Toast.makeText(this,R.string.error_insert,
                    Toast.LENGTH_SHORT).show()
                else ->  {
                    val transactionName = binding.editTextWriteMemoIncome.text.toString()
                    val transactionAmount = binding.editTextAmountIncome.text.toString().toInt()
                    val transactionType = TYPE_INCOME

                    //API 26
                    /*val today = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
                    val todayDate = today.format(formatter)*/

                    val today = Date()

                    val transaction = Transaction(0,transactionName,transactionType,transactionAmount,today)
                    viewModel.insertTransactions(transaction)
                    Toast.makeText(this, R.string.succes_insert_income, Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
            }
        }

    }
}
