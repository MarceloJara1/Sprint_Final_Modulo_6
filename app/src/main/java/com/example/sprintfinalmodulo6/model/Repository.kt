package com.example.sprintfinalmodulo6.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sprintfinalmodulo6.model.local.Dao
import com.example.sprintfinalmodulo6.model.local.entities.PhonesDetailEntity
import com.example.sprintfinalmodulo6.model.remote.RetrofitClient

class Repository( private val phoneDao: Dao) {

    private val networkService = RetrofitClient.retrofitInstance()

    val phonesListLiveData = phoneDao.getAllPhones()

    val phoneDetailLiveData = MutableLiveData<PhonesDetailEntity>()

    suspend fun fetchPhones(){
        val service = kotlin.runCatching { networkService.fetchPhonesList() }

        service.onSuccess {
            when(it.code()){
                in 200..299->it.body()?.let{
                    phoneDao.insertAllPhones(fromInternetToPhonesEntity(it))
                }
                else-> Log.d("REPO","${it.code()} - ${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }
        }
    }


    suspend fun fetchPhoneDetail(id: String): PhonesDetailEntity?{
        val service = kotlin.runCatching { networkService.fetchPhonesDetail(id) }
        return service.getOrNull()?.body()?.let { phonesDetail ->
            val phonesDetailEntity = fromInternetToPhoneDetailEntity(phonesDetail)
            phoneDao.insertPhonesDetail(phonesDetailEntity)
            phonesDetailEntity
        }
    }
}