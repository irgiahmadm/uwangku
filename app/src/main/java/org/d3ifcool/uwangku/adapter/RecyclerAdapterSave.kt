package org.d3ifcool.uwangku.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3ifcool.uwangku.R
import org.d3ifcool.uwangku.database.Save
import org.d3ifcool.uwangku.databinding.SaveItemBinding

class RecyclerAdapterSave(var context: Context,private val listener : (Save) -> Unit) :
        RecyclerView.Adapter<RecyclerAdapterSave.SaveViewHolder>(){

    private var listSave = arrayListOf<Save>()

    fun setListSave(listSave : ArrayList<Save>){
        this.listSave = listSave
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val binding : SaveItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.save_item,parent,false)
        return SaveViewHolder(binding)
    }

    override fun getItemCount() = listSave.size

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bindItem(listSave[position],listener)
    }

    class SaveViewHolder(val binding: SaveItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindItem(save : Save, listener: (Save) -> Unit){
            binding.save = save
            binding.executePendingBindings()
        }
    }

}