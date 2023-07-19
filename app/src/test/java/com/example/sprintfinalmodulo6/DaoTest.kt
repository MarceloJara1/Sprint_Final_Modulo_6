package com.example.sprintfinalmodulo6

import android.content.Context
import android.os.Build.VERSION_CODES.Q
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sprintfinalmodulo6.model.local.Dao
import com.example.sprintfinalmodulo6.model.local.database.PhoneDatabase
import com.example.sprintfinalmodulo6.model.local.entities.PhonesEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk=[Q], manifest = Config.NONE)
class DaoTest {

    private lateinit var daoTest: Dao
    private lateinit var db: PhoneDatabase

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PhoneDatabase::class.java).build()
        daoTest = db.getPhonesDao()
    }

    @After
    fun shutDown(){
        db.close()
    }

    @Test
    fun insertPhonesList()= runBlocking {
        val phonesEntity = listOf(
            PhonesEntity("13","Motorola Moto Edge 30 Fusion", 340000,"NoUrl"),
            PhonesEntity("14","Motorola Moto Edge 30 Ultra", 640000,"NoUrl")
        )
        daoTest.insertAllPhones(phonesEntity)
        val phonesLiveData = daoTest.getAllPhones()
        val phoneList: List<PhonesEntity> = phonesLiveData.value?: emptyList()

        assertThat(phoneList, not(emptyList()))
        assertThat(phoneList.size,equalTo(2))
    }
}