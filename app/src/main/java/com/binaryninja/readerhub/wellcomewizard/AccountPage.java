package com.binaryninja.readerhub.wellcomewizard;

import android.app.ProgressDialog;
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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.binaryninja.readerhub.MainActivity;
import com.binaryninja.readerhub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AccountPage extends Fragment {
    AppCompatButton signup, login, guest;
    AppCompatEditText email, password;
    FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wizard_signin, container, false);
        mAuth = FirebaseAuth.getInstance();
        signup = view.findViewById(R.id.wizard_login_signup);
        login = view.findViewById(R.id.wizard_register_login);
        guest = view.findViewById(R.id.wizard_login_guest);
        email = view.findViewById(R.id.wizard_login_username);
        password = view.findViewById(R.id.wizard_login_password);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                Fragment registerFragment = new RegisterPage();
                transaction.replace(R.id.wizard_parent, registerFragment);
                transaction.commitAllowingStateLoss();
                WizardMainViewModel.setPagerPos("signin");
            }
        });
        guest.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        });

        login.setOnClickListener(view1 -> {
            //TODO handle login from server
            String username, pass;
            username = email.getText().toString().trim();
            pass = password.getText().toString().trim();
            if (username.length() < 5)
                email.setError("Invalid Username");
            else if (pass.length() < 3)
                password.setError("Invalid password");
            else {
                login.setText("SignIn...");
                mAuth.signInWithEmailAndPassword(username, pass)
                        .addOnCompleteListener(getActivity(), task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                startActivity(new Intent(getContext(), MainActivity.class));
                                Toast.makeText(getContext(), "Authentication Success.",
                                        Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                login.setText(task.getException().getMessage());
                                Log.w("'TAG'", "signInWithEmail:failure", task.getException());
                                Toast.makeText(getContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        });
            }
        });
        return view;
    }

    public void loadResource() {
        // select_lang.setText(R.string.select_language);
    }
}
