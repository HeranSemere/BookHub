package com.binaryninja.readerhub.wellcomewizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.tools.Utils

class LangSelect : Fragment() {
    var rg1: RadioGroup? = null
    var rg2: RadioGroup? = null
    var wizardNext: AppCompatButton? = null
    var select_lang: AppCompatTextView? = null
    var listener1: RadioGroup.OnCheckedChangeListener? = null
    var listener2: RadioGroup.OnCheckedChangeListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.wizard_language, container, false)
        rg1 = view.findViewById(R.id.wizard_lang_r1)
        rg2 = view.findViewById(R.id.wizard_lang_r2)
        select_lang = view.findViewById(R.id.lang_title)
        wizardNext = view.findViewById(R.id.wizard_next)
        rg2?.clearCheck()
        rg1?.clearCheck()
        listener1 = RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            if (i != -1) {
                rg2?.setOnCheckedChangeListener(null) // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                rg2?.clearCheck() // clear the second RadioGroup!
                rg2?.setOnCheckedChangeListener(listener2) //reset the listener
                Utils.loadLanguage(requireContext(), if (i == R.id.wizard_lang_eng) "en" else "am-rET")
                loadResource()
            }
        }
        listener2 = RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            if (i != -1) {
                rg1?.setOnCheckedChangeListener(null)
                rg1?.clearCheck()
                rg1?.setOnCheckedChangeListener(listener1)
                Utils.loadLanguage(requireContext(), if (i == R.id.wizard_lang_oro) "om-rET" else "ti-rET")
                loadResource()
            }
        }
        rg1?.setOnCheckedChangeListener(listener1)
        rg2?.setOnCheckedChangeListener(listener2)
        return view
    }

    fun loadResource() {
        select_lang!!.setText(R.string.select_language)
    }
}