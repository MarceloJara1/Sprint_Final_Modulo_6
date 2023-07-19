package com.example.sprintfinalmodulo6.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sprintfinalmodulo6.model.local.entities.PhonesDetailEntity
import com.example.sprintfinalmodulo6.model.local.entities.PhonesEntity


@Database(entities = [PhonesEntity::class, PhonesDetailEntity::class], version =1,
exportSchema = false)
abstract class PhoneDatabase: RoomDatabase() {

    abstract fun getPhonesDao(): com.example.sprintfinalmodulo6.model.local.Dao

    companion object{
        @Volatile
        private var INSTANCE : PhoneDatabase?=null

        fun getDatabase(context: Context): PhoneDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDatabase::class.java,"phones_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}