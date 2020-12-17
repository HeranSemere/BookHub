package com.binaryninja.readerhub.wellcomewizard;

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

import com.binaryninja.readerhub.R;

public class Guide extends Fragment {
    AppCompatButton wizardNext;
    AppCompatTextView title,desc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wizard_guide,container,false);
        desc = view.findViewById(R.id.wizard_feature_desc);
        title = view.findViewById(R.id.wizard_feature_title);
        return view;
    }
    public void loadResource(){
    // select_lang.setText(R.string.select_language);
    }
}
