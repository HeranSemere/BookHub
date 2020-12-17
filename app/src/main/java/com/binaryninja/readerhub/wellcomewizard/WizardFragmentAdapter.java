package com.binaryninja.readerhub.wellcomewizard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class WizardFragmentAdapter extends FragmentPagerAdapter {
    public WizardFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LangSelect();
            case 1:
                return new Guide();
            case 2:
                return new GuideTwo();
            case 3:
                return new GuideThree();
            case 4:
                return new AccountPage();
        }
        return new LangSelect();
    }

    @Override
    public int getCount() {
        return 5;
    }
}