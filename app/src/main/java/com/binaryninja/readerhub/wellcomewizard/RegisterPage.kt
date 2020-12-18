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
import androidx.fragment.app.Fragment
import com.binaryninja.readerhub.MainActivity
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class RegisterPage : Fragment() {
    var wizardNext: AppCompatButton? = null
    var fname: AppCompatEditText? = null
    var email: AppCompatEditText? = null
    var address: AppCompatEditText? = null
    var password: AppCompatEditText? = null
    var cpassword: AppCompatEditText? = null
    private var mAuth: FirebaseAuth? = null
    private var firestore: FirebaseFirestore? = null
    private var register: AppCompatButton? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.wizard_register, container, false)
        mAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        register = view.findViewById(R.id.wizard_register_login)
        fname = view.findViewById(R.id.wizard_register_name)
        email = view.findViewById(R.id.wizard_register_username)
        address = view.findViewById(R.id.wizard_register_address)
        password = view.findViewById(R.id.wizard_register_password)
        cpassword = view.findViewById(R.id.wizard_register_confirm_password)
        register?.setOnClickListener { registerUserToDatabase() }
        WizardMainViewModel.handleChangeFragment().observe(viewLifecycleOwner, { s: String ->
            if (s == "signin") {
                val transaction = childFragmentManager.beginTransaction()
                val loginFragment: Fragment = SignInPage()
                transaction.replace(R.id.wizard_parent, loginFragment)
                transaction.commitAllowingStateLoss()
                WizardMainViewModel.changeFragment("")
            }
        })
        return view
    }

    fun loadResource() {
        // select_lang.setText(R.string.select_language);
    }

    private fun registerUserToDatabase() {
        //TODO save user account into server database
        val user = verifyUserData()
        if (user != null) {
            register!!.text = "Creating Account..."
            mAuth!!.createUserWithEmailAndPassword(user.username!!, user.password!!)
                .addOnCompleteListener(
                    requireActivity(), OnCompleteListener { task: Task<AuthResult?> ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success")
                            Toast.makeText(
                                context, "Registered Successfully.",
                                Toast.LENGTH_SHORT
                            ).show()
                            saveUserInfo(task.result?.user?.uid,user);
                            startActivity(Intent(context, MainActivity::class.java))
                            requireActivity().finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            register!!.text = "Retry : " + task.exception!!.message
                            Log.w("TAG", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                context, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
        }
    }

    private fun verifyUserData(): User? {
        val name: String = fname!!.text.toString().trim { it <= ' ' }
        val username: String = email!!.text.toString().trim { it <= ' ' }
        val addr: String = address!!.text.toString().trim { it <= ' ' }
        val pass: String = password!!.text.toString().trim { it <= ' ' }
        val cpass: String = cpassword!!.text.toString().trim { it <= ' ' }
        if (name.isEmpty()) fname!!.error =
            "invalid name" else if (username.length < 10) email!!.error =
            "invalid email" else if (addr.isEmpty()) address!!.error =
            "Invalid address" else if (pass.isEmpty() || pass != cpass) password!!.error =
            "invalid password" else {
            return User("", name, username, pass, addr)
        }
        return null
    }

    private fun saveUserInfo(id: String?, user: User) {
        val ref = firestore!!.collection("users")
        if (id != null && id.isNotEmpty()) ref.document(id)
        val hashMap = HashMap<String, Any?>()
        hashMap["name"] = user.name
        hashMap["email"] = user.username
        hashMap["address"] = user.address
        ref.add(hashMap)
    }
}