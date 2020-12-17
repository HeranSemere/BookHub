package com.binaryninja.readerhub.wellcomewizard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.binaryninja.readerhub.MainActivity;
import com.binaryninja.readerhub.R;

public class RegisterPage extends Fragment {
    AppCompatButton wizardNext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wizard_register,container,false);
        return view;
    }
    public void loadResource(){
    // select_lang.setText(R.string.select_language);
    }
}
