package com.binaryninja.readerhub.wellcomewizard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object WizardMainViewModel {
    private var setpager: MutableLiveData<String>? = null
    private var fragment: MutableLiveData<String>? = null
    fun setPagerPos(fragment: String) {
        if (setpager == null) setpager = MutableLiveData()
        setpager!!.value = fragment
    }

    val pagerPos: LiveData<String>?
        get() {
            if (setpager == null) {
                setpager = MutableLiveData()
                setpager!!.value = ""
            }
            return setpager
        }

    fun changeFragment(fragmentName: String) {
        if (fragment == null) fragment = MutableLiveData()
        fragment!!.value = fragmentName
        setPagerPos("")
    }

    fun handleChangeFragment(): LiveData<String>? {
        if (fragment == null) {
            fragment = MutableLiveData()
            fragment!!.value = ""
        }
        return fragment
    }
}