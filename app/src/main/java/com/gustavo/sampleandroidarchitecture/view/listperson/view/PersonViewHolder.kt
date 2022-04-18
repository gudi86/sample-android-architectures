package com.gustavo.sampleandroidarchitecture.view.listperson.view

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gustavo.sampleandroidarchitecture.R
import com.gustavo.sampleandroidarchitecture.databinding.PersonItemListBinding
import com.gustavo.sampleandroidarchitecture.model.Person

class PersonViewHolder(private val binding : PersonItemListBinding) : ViewHolder(binding.root) {

    fun bindItemInViewHolder(person: Person) {
        val context = itemView.context
        binding.lblFullname.text = context
            .getString(R.string.lbl_fullname, person.name, person.lastname)

        binding.lblBirthday.text = person.birthday
        binding.lblWeight.text = context
            .getString(R.string.lbl_weight, person.weight.toString())

    }
}
