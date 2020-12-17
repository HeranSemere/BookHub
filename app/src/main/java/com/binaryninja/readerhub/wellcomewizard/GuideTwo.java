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
    RadioGroup rg1,rg2;
    AppCompatButton wizardNext;
    AppCompatTextView select_lang;
    RadioGroup.OnCheckedChangeListener listener1,listener2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
       // rg1 = view.findViewById(R.id.wizard_lang_r1);

        wizardNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WizardMainViewModel.setPagerPos(1);
            }
        });
        return view;
    }
    public void loadResource(){
    // select_lang.setText(R.string.select_language);
    }
}
