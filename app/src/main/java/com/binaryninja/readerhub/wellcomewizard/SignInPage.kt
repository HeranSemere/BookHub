package com.binaryninja.readerhub.wellcomewizard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.binaryninja.readerhub.MainActivity
import com.binaryninja.readerhub.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class SignInPage : Fragment() {
    var login: AppCompatButton? = null
    var email: AppCompatEditText? = null
    var password: AppCompatEditText? = null
    var mAuth: FirebaseAuth? = null
    var signup: AppCompatButton? = null
    var guest: AppCompatButton? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.wizard_signin, container, false)
        mAuth = FirebaseAuth.getInstance()
        signup = view.findViewById(R.id.wizard_login_signup)
        login = view.findViewById(R.id.wizard_register_login)
        guest = view.findViewById(R.id.wizard_login_guest)
        email = view.findViewById(R.id.wizard_login_username)
        password = view.findViewById(R.id.wizard_login_password)
        signup?.setOnClickListener(View.OnClickListener { view12: View? ->
            val transaction = childFragmentManager.beginTransaction()
            val registerFragment: Fragment = RegisterPage()
            transaction.replace(R.id.wizard_parent, registerFragment)
            transaction.commitAllowingStateLoss()
            WizardMainViewModel.setPagerPos("signin")
        })
        guest?.setOnClickListener(View.OnClickListener { view1: View? ->
            mAuth!!.signInAnonymously()
            startActivity(Intent(context, MainActivity::class.java))
            requireActivity().finish()
        })
        login?.setOnClickListener(View.OnClickListener { view1: View? ->
            //TODO handle login from server
            val username: String = email?.text.toString().trim { it <= ' ' }
            val pass: String = password?.text.toString().trim { it <= ' ' }
            when {
                username.length < 5 -> email?.error = "Invalid Username"
                pass.length < 3 -> password?.error =
                    "Invalid password"
                else -> {
                    login?.text = "SignIn..."
                    mAuth!!.signInWithEmailAndPassword(username, pass)
                        .addOnCompleteListener(requireActivity()) { task: Task<AuthResult?> ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                startActivity(Intent(context, MainActivity::class.java))
                                Toast.makeText(
                                    context, "Authentication Success.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                requireActivity().finish()
                            } else {
                                // If sign in fails, display a message to the user.
                                login?.text = task.exception!!.message
                                Log.w("'TAG'", "signInWithEmail:failure", task.exception)
                                Toast.makeText(
                                    context, "Authentication failed.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        })
        return view
    }

    fun loadResource() {
        // select_lang.setText(R.string.select_language);
    }
}