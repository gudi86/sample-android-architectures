package com.gustavo.sampleandroidarchitecture.model.localsource

import kotlin.random.Random

object Utils {

    fun getFakeStatusSaveData() : Boolean {
        return Random(System.currentTimeMillis()).nextInt() % 2 == 0
    }
}