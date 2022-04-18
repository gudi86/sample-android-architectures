package com.gustavo.sampleandroidarchitecture.model

class GetPersonModel(
    private val dataBase: DataBase
) {

    fun loadPersonList(success: (List<Person>) -> Unit) {
        Thread {
            Thread.sleep(3000)
            success(dataBase.getPersonList())
        }.start()
    }
}