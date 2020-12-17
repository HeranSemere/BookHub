package com.binaryninja.readerhub.wellcomewizard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.binaryninja.readerhub.R;

public class AccountPage extends Fragment {
    AppCompatButton signup,login,guest;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wizard_signin,container,false);
        signup = view.findViewById(R.id.wizard_login_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                Fragment registerFragment = new RegisterPage();
                transaction.replace(R.id.wizard_parent,registerFragment);
                transaction.commitAllowingStateLoss();
            }
        });
        return view;
    }
    public void loadResource(){
    // select_lang.setText(R.string.select_language);
    }
}
