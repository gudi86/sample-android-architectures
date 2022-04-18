package com.gustavo.sampleandroidarchitecture.model

import android.provider.ContactsContract.Data
import com.gustavo.sampleandroidarchitecture.model.localsource.Utils
import kotlin.random.Random

interface DataBase {
    companion object {

        private var dataBase: DataBase? = null

        fun getInstance() : DataBase {
            if (dataBase == null) {
                dataBase = FakeDataBase()
            }
            return dataBase!!
        }
    }

    fun getPersonList() : List<Person>

    fun addPerson(person: Person) : Boolean
}

class FakeDataBase : DataBase {

    private val tablePerson = mutableListOf(
        Person(1,"Zackary", "Schaefer", "02/27/89", 64.0f),
        Person(2, "Zunaira", "Gonzalez", "02/11/92", 62.0f),
        Person(3, "Zach", "Holmes", "07/12/88", 63.0f),
        Person(4, "Cai", "Melia", "08/24/93", 76.0f),
        Person(5, "Avi", "Whitmore", "02/09/96", 86.0f),
        Person(6, "Olivia", "Davies", "06/18/90", 90.0f),
        Person(7, "Rose", "Liu", "11/24/92", 91.0f),
        Person(8, "Jeanne", "Lake", "06/10/91", 92.0f),
        Person(9, "Glenn", "Gill", "09/18/96", 99.0f),
        Person(10, "Nyah", "Hoffman", "07/15/95", 67.0f),)

    override fun getPersonList() = tablePerson.toList()


    override fun addPerson(person: Person) : Boolean {

        if (Utils.getFakeStatusSaveData()) return false

        tablePerson.add(person)
        return true
    }


}