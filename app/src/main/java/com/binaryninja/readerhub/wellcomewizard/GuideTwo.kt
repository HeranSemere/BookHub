package com.binaryninja.readerhub.wellcomewizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.binaryninja.readerhub.R

class GuideTwo : Fragment() {
    var wizardNext: AppCompatButton? = null
    var title: AppCompatTextView? = null
    var desc: AppCompatTextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.wizard_guide_two, container, false)
        desc = view.findViewById(R.id.wizard_feature_desc)
        title = view.findViewById(R.id.wizard_feature_title)
        return view
    }

    fun loadResource() {
        // select_lang.setText(R.string.select_language);
    }
}