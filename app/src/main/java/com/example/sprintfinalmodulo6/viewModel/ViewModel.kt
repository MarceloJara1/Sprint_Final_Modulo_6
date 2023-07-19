package com.example.sprintfinalmodulo6.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sprintfinalmodulo6.model.Repository
import com.example.sprintfinalmodulo6.model.local.database.PhoneDatabase
import com.example.sprintfinalmodulo6.model.local.entities.PhonesDetailEntity
import com.example.sprintfinalmodulo6.model.local.entities.PhonesEntity
import kotlinx.coroutines.launch

class ViewModel (application: Application): AndroidViewModel(application){

    private val repository: Repository

    private val phoneDetailLiveData = MutableLiveData<PhonesDetailEntity>()

    private val idSelected: String = "-1"

    init {
        val bd = PhoneDatabase.getDatabase(application)
        val dao = bd.getPhonesDao()

        repository = Repository(dao)
        viewModelScope.launch {
            repository.fetchPhones()
        }
    }

    fun getPhonesList(): LiveData<List<PhonesEntity>> = repository.phonesListLiveData

    fun getPhonesDetail(): LiveData<PhonesDetailEntity> = phoneDetailLiveData

    fun getPhoneDetailByIdFromInternet(id:String) = viewModelScope.launch {
        val phoneDetail = repository.fetchPhoneDetail(id)
        phoneDetail?.let {
            phoneDetailLiveData.postValue(it)
        }
    }


}