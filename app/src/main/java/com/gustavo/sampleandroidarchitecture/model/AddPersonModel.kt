package com.gustavo.sampleandroidarchitecture.model

class AddPersonModel(
    private val dataBase: DataBase
) {

    fun addPerson(person: Person, success: () -> Unit, fail: (String) -> Unit) {
        Thread {
            Thread.sleep(3000)
            if(dataBase.addPerson(person)) success()
            else fail("Error save a new person. Try again")
        }.start()
    }
}