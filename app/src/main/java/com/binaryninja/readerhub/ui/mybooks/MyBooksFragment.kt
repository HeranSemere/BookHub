package com.binaryninja.readerhub.ui.mybooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.ui.profile.MyBooksViewModel

class MyBooksFragment : Fragment() {

    private lateinit var dashboardViewModel: MyBooksViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(MyBooksViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_mybook, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}