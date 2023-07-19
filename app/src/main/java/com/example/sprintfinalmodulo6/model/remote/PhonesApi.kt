package com.example.sprintfinalmodulo6.model.remote

import com.example.sprintfinalmodulo6.model.remote.fromInternet.Phones
import com.example.sprintfinalmodulo6.model.remote.fromInternet.PhonesDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhonesApi {

    @GET("products")
    suspend fun fetchPhonesList():Response<List<Phones>>

    @GET("details/{id}")
    suspend fun fetchPhonesDetail(@Path("id")id:String):Response<PhonesDetail>
}