package com.github.bgrebennikov.devbuff.presentation.ui.adapters.binding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.github.bgrebennikov.devbuff.R
import com.github.bgrebennikov.devbuff.data.local.explore.ideaDetails.MappedIdeaSpecialistsLanguages

@BindingAdapter("languagesToString")
fun setLanguagesToString(textView: TextView, languages :List<MappedIdeaSpecialistsLanguages>){

    textView.apply {

        if(languages.isEmpty()) {
            visibility = View.GONE
            return@apply
        }

        text = context.getString(R.string.stack_of_languages)
            .format(
                languages.joinToString(", "){
                    it.name
                }
            )
    }
}