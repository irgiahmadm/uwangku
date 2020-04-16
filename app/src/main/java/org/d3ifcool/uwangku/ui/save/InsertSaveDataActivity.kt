package org.d3ifcool.uwangku.ui.save

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.d3ifcool.uwangku.databinding.ActivityInsertSaveDataBinding
import java.util.*
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import org.d3ifcool.uwangku.R
import org.d3ifcool.uwangku.database.Save
import org.d3ifcool.uwangku.database.UwangkuDatabase
import java.text.SimpleDateFormat


class InsertSaveDataActivity : AppCompatActivity() {

    private lateinit var viewModel: SaveViewModel
    private lateinit var binding : ActivityInsertSaveDataBinding

    @TargetApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = this.application
        val dataSource = UwangkuDatabase.getInstance(application).transactionDAO

        val viewModelFactory =
            SaveViewModelFactory(dataSource)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SaveViewModel::class.java)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_insert_save_data)
        binding.textViewTargetTime.setOnClickListener {
            getDatePicker()
        }

        binding.buttonAddSave.setOnClickListener{
            when{
                binding.editTextWriteMemoSave.text.isEmpty() -> Toast.makeText(this, R.string.error_insert,
                    Toast.LENGTH_SHORT).show()
                binding.editTextAmountSave.text.isEmpty() -> Toast.makeText(this,R.string.error_insert,
                    Toast.LENGTH_SHORT).show()
                binding.textViewTargetTime.text == getString(R.string.date_hint) -> Toast.makeText(this,R.string.error_insert,
                    Toast.LENGTH_SHORT).show()
                else -> {
                    val transactionName = binding.editTextWriteMemoSave.text.toString()
                    val saveAmount = binding.editTextAmountSave.text.toString().toInt()
                    val targetTime = binding.textViewTargetTime.text.toString()

                    //TODO CHANGE SAVE
                    val save = Save(0,transactionName,0,saveAmount,targetTime)
                    viewModel.insertSave(save)
                    Toast.makeText(this, R.string.succes_insert_save, Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDatePicker(){

        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        c.add(Calendar.DAY_OF_YEAR, 1)
        val dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, _year, _, dayOfMonth ->
            val strDate = SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("dd MMM, yyyy")

            //TODO add formatter for API 26 or above
            val formatedDate = formatter.format(strDate.parse("$_year-0$month-$dayOfMonth")!!)

            binding.textViewTargetTime.text = formatedDate.toString()
        }, year, month, day)

        dialog.datePicker.minDate = c.timeInMillis
        dialog.show()
    }
}
