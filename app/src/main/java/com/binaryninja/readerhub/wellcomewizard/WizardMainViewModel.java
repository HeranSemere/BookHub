package com.binaryninja.readerhub.wellcomewizard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class WizardMainViewModel {
    private static MutableLiveData<String> setpager;
    private static MutableLiveData<String> fragment;
    public static void setPagerPos(String fragment){
        if (setpager==null)
            setpager = new MutableLiveData<>();
        setpager.setValue(fragment);
    }
    public static LiveData<String> getPagerPos(){
        if (setpager==null){
            setpager = new MutableLiveData<>();
            setpager.setValue("");
        }
        return setpager;
    }
    public static void changeFragment(String fragmentName){
        if (fragment==null)
            fragment = new MutableLiveData<>();
        fragment.setValue(fragmentName);
        setPagerPos("");
    }
    public static LiveData<String> handleChangeFragment(){
        if (fragment==null){
            fragment = new MutableLiveData<>();
            fragment.setValue("");
        }
        return fragment;
    }
}
