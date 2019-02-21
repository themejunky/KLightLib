package com.keyboard_theme_manager;

import android.support.v4.view.ViewPager;

import com.keyboard_theme_manager.screens.adapter.AdapterTabViewPager;
import com.keyboard_theme_manager.screens.fragments.themes.ThemesFragment;


public class MainFlavorActivity extends MainBaseActivity {


    protected void setUpViewPager() {


        mFragmentInstanceThemes = new ThemesFragment();

        AdapterTabViewPager adapterTabViewPager = new AdapterTabViewPager(getSupportFragmentManager());
        adapterTabViewPager.addFragment(mFragmentInstanceThemes,"Themes");
        viewPager.setAdapter(adapterTabViewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                           if (mFragmentInstanceThemes != null) {   ThemesFragment_onResumeOrOnPause(true); }
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
