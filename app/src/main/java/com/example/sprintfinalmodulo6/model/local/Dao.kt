package com.example.sprintfinalmodulo6.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sprintfinalmodulo6.model.local.entities.PhonesDetailEntity
import com.example.sprintfinalmodulo6.model.local.entities.PhonesEntity

@Dao
interface Dao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhones(listPhones: List<PhonesEntity>)

    @Query("SELECT * FROM phone_list")
    fun getAllPhones():LiveData<List<PhonesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhonesDetail(phone: PhonesDetailEntity)
}