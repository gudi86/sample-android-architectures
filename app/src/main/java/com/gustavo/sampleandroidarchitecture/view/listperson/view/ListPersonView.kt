package com.gustavo.sampleandroidarchitecture.view.listperson.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import com.gustavo.sampleandroidarchitecture.databinding.ListPersonViewBinding
import com.gustavo.sampleandroidarchitecture.model.DataBase
import com.gustavo.sampleandroidarchitecture.model.GetPersonModel
import com.gustavo.sampleandroidarchitecture.view.base.BaseView

interface ListPersonView : BaseView<ListPersonViewBinding> {
    fun refreshPersonList()

    fun getTitleToolBar() : String
}

class ListPersonViewImpl(
    private val context: Context
) : ListPersonView {

    private val model = GetPersonModel(DataBase.getInstance())

    override val binding: ListPersonViewBinding by lazy {
        val inflater = LayoutInflater.from(context)
        ListPersonViewBinding.inflate(inflater)
    }

    private val adapter = PersonAdapter()

    init {
        binding.recyclerViewPersons.adapter = adapter
    }

    override fun refreshPersonList() {
        loadPersonList()
        model.loadPersonList {
            Handler(Looper.getMainLooper())
                .post {
                    adapter.submitList(it) {
                        finishLoadingPersonList()
                    }
                }
        }
    }

    override fun getTitleToolBar() = "MVC Architecture"

    private fun loadPersonList() {
        binding.prgLoadingListPerson.visibility = View.VISIBLE
        binding.recyclerViewPersons.visibility = View.INVISIBLE
    }

    private fun finishLoadingPersonList() {
        binding.prgLoadingListPerson.visibility = View.GONE
        binding.recyclerViewPersons.visibility = View.VISIBLE
    }

}