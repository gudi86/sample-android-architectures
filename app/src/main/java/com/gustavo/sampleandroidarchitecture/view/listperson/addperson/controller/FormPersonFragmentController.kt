package com.gustavo.sampleandroidarchitecture.view.listperson.addperson.controller

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gustavo.sampleandroidarchitecture.model.AddPersonModel
import com.gustavo.sampleandroidarchitecture.model.DataBase
import com.gustavo.sampleandroidarchitecture.model.Person
import com.gustavo.sampleandroidarchitecture.view.listperson.ListPersonActivityController
import com.gustavo.sampleandroidarchitecture.view.listperson.ListPersonActivityController.Companion
import com.gustavo.sampleandroidarchitecture.view.listperson.ListPersonActivityController.Companion.KEY_SUCCES_ADD
import com.gustavo.sampleandroidarchitecture.view.listperson.addperson.view.FormPersonView
import com.gustavo.sampleandroidarchitecture.view.listperson.addperson.view.FormPersonViewImpl

class FormPersonFragmentController: BottomSheetDialogFragment() {

    private val formPersonView: FormPersonView by lazy {
        FormPersonViewImpl(requireContext())
    }

    private val model = AddPersonModel(DataBase.getInstance())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = formPersonView.binding

        binding.btnAddPerson.setOnClickListener(::addNewPerson)

        return binding.root
    }

    private fun addNewPerson(view: View) {

        if (validateFields()) return

        val binding = formPersonView.binding
        val person = Person(
            name = binding.edtName.text.toString(),
            lastname = binding.edtLastname.text.toString(),
            birthday = binding.edtBirthday.text.toString(),
            weight = binding.edtWeight.text.toString().toFloat()
        )

        formPersonView.showLoading()

        model.addPerson(person, {
            Handler(Looper.getMainLooper()).post {

                parentFragmentManager.setFragmentResult(ListPersonActivityController.SUCCESS_ADD_NEW_PERSON,
                    bundleOf(KEY_SUCCES_ADD to true))
                formPersonView.showAlertMessage("Save new person")
                dismiss()
            }
        }) {
            Handler(Looper.getMainLooper()).post {
                formPersonView.showProcessingFinished()
                formPersonView.showAlertMessage(it)
            }
        }
    }

    private fun validateFields() : Boolean {
        val binding = formPersonView.binding
        if (binding.edtName.text.isEmpty()) {
            formPersonView.showAlertMessage("Name is empty")
            return true
        }

        if (binding.edtBirthday.text.isEmpty()) {
            formPersonView.showAlertMessage("Birthday is empty")
            return true
        }

        if (binding.edtLastname.text.isEmpty()) {
            formPersonView.showAlertMessage("Last name is empty")
            return true
        }

        if (binding.edtWeight.text.isEmpty()) {
            formPersonView.showAlertMessage("Weight is empty")
            return true
        }

        return false
    }


}