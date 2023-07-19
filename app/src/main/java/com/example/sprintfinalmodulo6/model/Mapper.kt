package com.example.sprintfinalmodulo6.model

import com.example.sprintfinalmodulo6.model.local.entities.PhonesDetailEntity
import com.example.sprintfinalmodulo6.model.local.entities.PhonesEntity
import com.example.sprintfinalmodulo6.model.remote.fromInternet.Phones
import com.example.sprintfinalmodulo6.model.remote.fromInternet.PhonesDetail

fun fromInternetToPhonesEntity(phoneList:List<Phones>): List<PhonesEntity>{

    return phoneList.map{
        PhonesEntity(
            id = it.id,
            name = it.name,
            price = it.price,
            image = it.image

        )
    }

}

fun fromInternetToPhoneDetailEntity(phone:PhonesDetail): PhonesDetailEntity{

    return PhonesDetailEntity(
        id = phone.id,
        name = phone.name,
        price = phone.price,
        image = phone.image,
        description = phone.description,
        lastPrice = phone.lastPrice,
        credit = phone.credit

    )
}