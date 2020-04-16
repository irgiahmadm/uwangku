package org.d3ifcool.uwangku.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3ifcool.uwangku.R
import org.d3ifcool.uwangku.database.Transaction
import org.d3ifcool.uwangku.databinding.TransactionItemBinding
import org.d3ifcool.uwangku.ui.transaction.TYPE_EXPENSE
import org.d3ifcool.uwangku.ui.transaction.TYPE_INCOME

class RecyclerAdapterTransaction(var context: Context,private val listener : (Transaction) -> Unit) :
    RecyclerView.Adapter<RecyclerAdapterTransaction.TransactionViewHolder>() {

    private var listTransaction = arrayListOf<Transaction>()

    fun setListTransaction(listTransction : ArrayList<Transaction>){
        this.listTransaction = listTransction
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        context = parent.context
        val binding : TransactionItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.transaction_item,parent,false)
        return TransactionViewHolder(binding)
    }

    override fun getItemCount(): Int  = listTransaction.size

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bindItem(listTransaction[position],listener)
    }

    class TransactionViewHolder(val binding: TransactionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindItem(transactions:Transaction, listener: (Transaction) -> Unit) {

            if(transactions.jenis == TYPE_EXPENSE){
                binding.textViewAmount.text = "-" + transactions.total.toString()
                binding.viewType.setBackgroundResource(R.drawable.expense_type)

            }else if(transactions.jenis == TYPE_INCOME){
                binding.textViewAmount.text = "+" + transactions.total.toString()
                binding.viewType.setBackgroundResource(R.drawable.income_type)
            }
            binding.transaction = transactions
            binding.executePendingBindings()
        }
    }
}