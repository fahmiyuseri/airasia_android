package com.ipay88.airasia.Activity.ui.main

import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.util.Log
import com.ipay88.airasia.R
import android.os.Bundle
import android.support.v4.app.FragmentActivity


private val TAB_TITLES = arrayOf(
    R.string.tab_text_1
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: FragmentActivity    ,private var fm: FragmentManager,private var listdate : List<String>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Log.d("sada","create")
        return PlaceholderFragment.newInstance(position + 1,listdate.get(position))
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return listdate.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    fun setListdate(listdate: List<String>) {
        this.listdate = listdate
        notifyDataSetChanged()
    }

}