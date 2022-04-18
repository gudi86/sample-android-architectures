package com.gustavo.sampleandroidarchitecture.view.listperson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gustavo.sampleandroidarchitecture.R
import com.gustavo.sampleandroidarchitecture.view.listperson.addperson.controller.FormPersonFragmentController
import com.gustavo.sampleandroidarchitecture.view.listperson.view.ListPersonView
import com.gustavo.sampleandroidarchitecture.view.listperson.view.ListPersonViewImpl

class ListPersonActivityController : AppCompatActivity() {

    companion object {
        const val SUCCESS_ADD_NEW_PERSON = "SUCCESS_ADD_NEW_PERSON"
        const val KEY_SUCCES_ADD = "KEY_SUCCES_ADD"
        const val INPUT_PERSON_VIEW = "INPUT_PERSON_VIEW"
    }

    private val listPersonView : ListPersonView by lazy {
        ListPersonViewImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = listPersonView.binding
        setContentView(binding.root)

        title = listPersonView.getTitleToolBar()

        binding.btnAddPerson.setOnClickListener {
            val formPersonView = FormPersonFragmentController()
            formPersonView.show(supportFragmentManager, INPUT_PERSON_VIEW)
        }
        supportFragmentManager.
        setFragmentResultListener(
            SUCCESS_ADD_NEW_PERSON, this) { requestKey, bundle ->
            val result = bundle.getBoolean(KEY_SUCCES_ADD)
            if (result)
                listPersonView.refreshPersonList()

        }

        listPersonView.refreshPersonList()
    }
}