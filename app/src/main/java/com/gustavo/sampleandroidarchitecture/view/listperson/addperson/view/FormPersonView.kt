package com.gustavo.sampleandroidarchitecture.view.listperson.addperson.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.gustavo.sampleandroidarchitecture.databinding.FormPersonViewBinding
import com.gustavo.sampleandroidarchitecture.view.base.BaseView

interface FormPersonView : BaseView<FormPersonViewBinding> {
        fun showAlertMessage(message: String)

        fun showLoading()

        fun showProcessingFinished()

}

class FormPersonViewImpl(
    private val context: Context
) : FormPersonView {

    override val binding: FormPersonViewBinding by lazy {
        val inflater = LayoutInflater.from(context)
        FormPersonViewBinding.inflate(inflater)

    }

    init {
        binding.prgAddPerson.visibility = View.GONE
    }

    override fun showAlertMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG)
            .show()
    }

    override fun showLoading() {
        binding.prgAddPerson.visibility = View.VISIBLE
        binding.btnAddPerson.visibility = View.GONE
    }

    override fun showProcessingFinished() {
        binding.prgAddPerson.visibility = View.GONE
        binding.btnAddPerson.visibility = View.VISIBLE
    }

}