package com.binaryninja.readerhub.wellcomewizard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import com.binaryninja.readerhub.MainActivity;
import com.binaryninja.readerhub.R;
import com.binaryninja.readerhub.custom_view.PageIndicator;
import com.binaryninja.readerhub.tools.Constant;
import com.binaryninja.readerhub.tools.SharedPref;


public class WizardMain extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SharedPref pref = new SharedPref(getApplicationContext());
        if (!pref.getBool(Constant.PREF_FIRST_RUN, true)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final ViewPager pager = findViewById(R.id.wizard_pager);
        final PageIndicator indicator = findViewById(R.id.wizard_pager_indicator);
        WizardFragmentAdapter adapter = new WizardFragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicator.setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        WizardMainViewModel.getPagerPos().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                pager.setCurrentItem(integer);
                Log.i("Wizard Main", "ViewModel Pager postion " + integer);
            }
        });
    }
}
