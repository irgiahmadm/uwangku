package org.d3ifcool.uwangku.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Transaction::class, Save::class], version = 1,  exportSchema = false)
abstract class UwangkuDatabase : RoomDatabase(){

    abstract val transactionDAO : TransactionDAO

    companion object{

        @Volatile
        private var INSTANCE : UwangkuDatabase? = null

        fun getInstance(context: Context) : UwangkuDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UwangkuDatabase::class.java,
                        "uwangku_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}