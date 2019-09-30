package com.ipay88.airasia.Activity.ui.main

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import com.ipay88.airasia.R
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_search_flight.*
import kotlinx.android.synthetic.main.fragment_search_flight.view.*

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    var pageNum = 0
    var datestr = ""
     var root : View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNum = arguments!!.getInt(ARG_SECTION_NUMBER)
        datestr = arguments!!.getString(ARG_SECTION_DATE)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         root = inflater.inflate(R.layout.fragment_search_flight, container, false)

        //textView.text = "page num $pageNum date $datestr"

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_SECTION_DATE = "date_str"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int,strDate : String): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(ARG_SECTION_DATE, strDate)
                }
            }
        }
    }
    fun updateList(strdate : String?){
        if(root!=null)
        root!!.section_label.text = "page num date $strdate"

    }
    override fun onResume() {
        super.onResume()
        Log.d("sada","sdsds")
        Log.d("sada","page num date $datestr")
        root!!.section_label.text = "page num date $datestr"

    }
}