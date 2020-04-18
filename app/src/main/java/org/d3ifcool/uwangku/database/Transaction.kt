package org.d3ifcool.uwangku.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "transaksi")
data class  Transaction(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    @ColumnInfo(name = "nama_transaksi")
    var namaTransaksi:String,

    @ColumnInfo(name = "jenis")
    var jenis:String,

    @ColumnInfo(name = "total")
    var total : Int,

    @ColumnInfo(name = "tanggal")
    var tglTransaksi: Date?

)