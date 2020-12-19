package com.binaryninja.readerhub.wellcomewizard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class WizardFragmentAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return LangSelect()
            1 -> return Guide()
            2 -> return GuideTwo()
            3 -> return GuideThree()
            4 -> return SignInPage()
        }
        return LangSelect()
    }

    override fun getCount(): Int {
        return 5
    }
}