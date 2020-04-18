package org.d3ifcool.uwangku.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "save")
data class Save(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    @ColumnInfo(name = "name_transaksi")
    var namaTransaksi:String,

    @ColumnInfo(name = "uang_terkumpul")
    var uangTerkumpul : Int,

    @ColumnInfo(name = "total_akhir")
    var totalAkhir : Int,

    @ColumnInfo(name = "target_tanggal")
    var targetTanggal: Date?,

    @ColumnInfo(name = "tgl_buat")
    var dibuatTanggal:Date?

)