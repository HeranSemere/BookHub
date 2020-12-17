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

public class RegisterPage extends Fragment {
    AppCompatButton wizardNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wizard_register, container, false);
        AppCompatButton v = view.findViewById(R.id.wizard_register_login);
        v.setOnClickListener(view1 -> {
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
    }
}
