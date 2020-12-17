package com.binaryninja.readerhub.wellcomewizard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.binaryninja.readerhub.MainActivity;
import com.binaryninja.readerhub.R;
import com.binaryninja.readerhub.custom_view.PageIndicator;
import com.binaryninja.readerhub.tools.Constant;
import com.binaryninja.readerhub.tools.SharedPref;


public class WizardMain extends AppCompatActivity {
    private static int pos = 0;
    AppCompatButton next;
    ViewPager pager;
    WizardFragmentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        next = findViewById(R.id.wizard_pager_next);
        SharedPref pref = new SharedPref(getApplicationContext());
        if (!pref.getBool(Constant.PREF_FIRST_RUN, true)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pager = findViewById(R.id.wizard_pager);
        final PageIndicator indicator = findViewById(R.id.wizard_pager_indicator);
        adapter = new WizardFragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pager.setAdapter(adapter);
        next.setOnClickListener(view -> pager.setCurrentItem(pager.getCurrentItem() + 1));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicator.setCurrentPage(position);
                changeNextButtonState("", position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        WizardMainViewModel.getPagerPos().observe(this, fragment -> changeNextButtonState(fragment, pager.getCurrentItem()));
    }

    private void changeNextButtonState(String fragment, int position) {
        Log.e(">>>>>>>>>>>>>>>>>", "changeNextButtonState: "+fragment+" = " +position);
        if (position < 4) {
            next.setText("Next");
            next.setVisibility(View.VISIBLE);
            next.setOnClickListener(view -> pager.setCurrentItem(pager.getCurrentItem() + 1));
        } else if (fragment.equalsIgnoreCase("signin")) {
            next.setText("Sign in");
            next.setVisibility(View.VISIBLE);
            next.setOnClickListener(view -> {
                WizardMainViewModel.changeFragment("signin");
            });
        } else next.setVisibility(View.INVISIBLE);
    }
}
