package org.d3ifcool.uwangku.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDAO{

    //Transaction data
    @Insert
    fun insertTransaksi(transaction: Transaction)

    @Query("SELECT * FROM transaksi WHERE strftime('%Y-%m', tanggal) = :month-:year")
    fun getDataByMonthAndYear(month:String,year:String) : LiveData<List<Transaction>>

    @Query("SELECT * FROM transaksi")
    fun getAllData() : LiveData<List<Transaction>>

    @Query("SELECT SUM(total) FROM transaksi WHERE jenis = :jenis")
    fun getTotalByType(jenis : String) : Int

    //Save/Wishlist data
    @Insert
    fun insertSave(save:Save)

    @Query("SELECT * FROM save")
    fun getAllSaveData() : LiveData<List<Save>>

    @Query("SELECT COUNT(*) FROM save")
    fun getSizeSaveData() : LiveData<Int>?

}