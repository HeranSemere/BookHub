package com.binaryninja.readerhub.wellcomewizard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.binaryninja.readerhub.MainActivity
import com.binaryninja.readerhub.R
import com.binaryninja.readerhub.custom_view.PageIndicator
import com.binaryninja.readerhub.tools.Constant
import com.binaryninja.readerhub.tools.SharedPref

class WizardMain : AppCompatActivity() {
    var next: AppCompatButton? = null
    var pager: ViewPager? = null
    var adapter: WizardFragmentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        next = findViewById(R.id.wizard_pager_next)
        val pref = SharedPref(applicationContext)
        if (!pref.getBool(Constant.PREF_FIRST_RUN, true)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        pager = findViewById(R.id.wizard_pager)
        val indicator = findViewById<PageIndicator>(R.id.wizard_pager_indicator)
        adapter = WizardFragmentAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        pager?.adapter = adapter
        next?.setOnClickListener(View.OnClickListener { pager?.currentItem =
            pager?.currentItem?.plus(1)!!
        })
        pager?.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                indicator.setCurrentPage(position)
                changeNextButtonState("", position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        WizardMainViewModel.pagerPos?.observe(
            this,
            { fragment: String -> pager?.currentItem?.let {
                changeNextButtonState(fragment,
                    it
                )
            } })
    }

    private fun changeNextButtonState(fragment: String, position: Int) {
        Log.e(">>>>>>>>>>>>>>>>>", "changeNextButtonState: $fragment = $position")
        if (position < 4) {
            next!!.text = "Next"
            next!!.visibility = View.VISIBLE
            next!!.setOnClickListener { view: View? ->
                pager!!.currentItem = pager!!.currentItem + 1
            }
        } else if (fragment.equals("signin", ignoreCase = true)) {
            next!!.text = "Sign in"
            next!!.visibility = View.VISIBLE
            next!!.setOnClickListener { view: View? -> WizardMainViewModel.changeFragment("signin") }
        } else next!!.visibility = View.INVISIBLE
    }

    companion object {
        private const val pos = 0
    }
}