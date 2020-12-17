package com.binaryninja.readerhub.wellcomewizard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.binaryninja.readerhub.MainActivity;
import com.binaryninja.readerhub.R;
import com.binaryninja.readerhub.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPage extends Fragment {
    AppCompatButton wizardNext;
    AppCompatEditText fname, email, address, password, cpassword;
    private FirebaseAuth mAuth;
    AppCompatButton register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wizard_register, container, false);
        mAuth = FirebaseAuth.getInstance();

        register = view.findViewById(R.id.wizard_register_login);
        fname = view.findViewById(R.id.wizard_register_name);
        email = view.findViewById(R.id.wizard_register_username);
        address = view.findViewById(R.id.wizard_register_address);
        password = view.findViewById(R.id.wizard_register_password);
        cpassword = view.findViewById(R.id.wizard_register_confirm_password);

        register.setOnClickListener(view1 -> {
            registerUserToDatabase();
        });
        WizardMainViewModel.handleChangeFragment().observe(getViewLifecycleOwner(), s -> {
            if (s.equals("signin")) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                Fragment loginFragment = new AccountPage();
                transaction.replace(R.id.wizard_parent, loginFragment);
                transaction.commitAllowingStateLoss();
                WizardMainViewModel.changeFragment("");
            }
        });
        return view;
    }

    public void loadResource() {
        // select_lang.setText(R.string.select_language);
    }

    public void registerUserToDatabase() {
        //TODO save user account into server database
        User user = verifyUserData();
        if (user != null) {
            register.setText("Creating Account...");
            mAuth.createUserWithEmailAndPassword(user.getUsername(), user.getPassword()).addOnCompleteListener(getActivity(), (OnCompleteListener<AuthResult>) task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success");
                    Toast.makeText(getContext(), "Registered Successfully.",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), MainActivity.class));
                    getActivity().finish();
                } else {
                    // If sign in fails, display a message to the user.
                    register.setText("Retry : " + task.getException().getMessage());
                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(getContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public User verifyUserData() {
        String name, username, addr, pass, cpass;
        name = fname.getText().toString().trim();
        username = email.getText().toString().trim();
        addr = address.getText().toString().trim();
        pass = password.getText().toString().trim();
        cpass = cpassword.getText().toString().trim();
        if (name.isEmpty())
            fname.setError("invalid name");
        else if (username.length() < 10)
            email.setError("invalid email");
        else if (addr.isEmpty())
            address.setError("Invalid address");
        else if (!pass.isEmpty() && pass.equalsIgnoreCase(cpass))
            password.setError("invalid password");
        else {
            return new User("", name, username, pass, addr);
        }
        return null;
    }
}
