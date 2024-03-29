package com.binaryninja.readerhub.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.data.Message
import com.binaryninja.readerhub.data.User
import kotlinx.android.synthetic.main.fragment_messages.*
import kotlinx.android.synthetic.main.fragment_mybook.*

class MessagesFragment : Fragment() {

    private lateinit var messageViewModel: ExchangeViewModel

    private var message = mutableListOf<Message>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        /*messageViewModel =
                ViewModelProviders.of(this).get(ExchangeViewModel::class.java)

        val textView: TextView = root.findViewById(R.id.text)
        messageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/


        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      
        message.add(Message(R.drawable.profile4,"Deborah","Hello, I was wondering if i can...","A Promised Land","8:42", R.drawable.notification,false))
        message.add(Message(R.drawable.profile3,"Mike","Yeah","Animal Farm","Sat",0,true))
        message.add(Message(R.drawable.profile7,"Demekech","Sure","A Passage To India","Sat",0, true))
        message.add(Message(R.drawable.profile5,"Kaleb","\uD83D\uDC4D","Alice's Adventures in Wonderland","Fri",0, false))
        message.add(Message(R.drawable.profile6,"Dumbledore","That is correct.","1984","Dec 10",0,false))


        messages_recyclerView.layoutManager = LinearLayoutManager(activity)
        messages_recyclerView.adapter = MessagesRecyclerAdapter(message)


    }
}