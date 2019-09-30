package com.ipay88.airasia.BottomSheetFragment

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import com.ipay88.airasia.R
import kotlinx.android.synthetic.main.infant_bottomsheet.*
import kotlinx.android.synthetic.main.infant_bottomsheet.view.*
import android.support.design.widget.CoordinatorLayout
import android.util.Log


class InfantSheetFragment : BottomSheetDialogFragment() {
    var infantListener : InfantListener? = null

    interface InfantListener{
        fun onChooseInfant(adult:Int,kids:Int,infants:Int)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.infant_bottomsheet, container, false)
view.setBackgroundColor(resources.getColor(android.R.color.transparent))

        view.btnSubmit.setOnClickListener {
            if(infantListener!=null){
                infantListener!!.onChooseInfant(tvTotalAdults.text.toString().toInt(),tvTotalKids.text.toString().toInt(),tvTotalInfant.text.toString().toInt());
                this.dismiss()
            }
        }

        view.tvAddInfants.setOnClickListener {
            Log.d("sada","clicked")
            var totalInfant =0
            totalInfant=tvTotalInfant.text.toString().toInt()
            totalInfant++
            tvTotalInfant.setText(totalInfant.toString())
        }
        view.tvAddKids.setOnClickListener {
            var total =0
            total=tvTotalKids.text.toString().toInt()
            total++
            tvTotalKids.text=total.toString()
        }
        view.tvAddAdult.setOnClickListener {
            var total =0
            total=tvTotalAdults.text.toString().toInt()
            total++
            tvTotalAdults.text=total.toString()
        }
        view.tvMinusAdult.setOnClickListener {
            var total =0
            total=tvTotalAdults.text.toString().toInt()
            total--
            tvTotalAdults.text=total.toString()
        }
        view.tvMinusInfant.setOnClickListener {
            var total =0
            total=tvTotalInfant.text.toString().toInt()
            total--
            tvTotalInfant.text=total.toString()
        }
        view.tvMinusKids.setOnClickListener {
            var total =0
            total=tvTotalKids.text.toString().toInt()
            total--
            tvTotalKids.text=total.toString()
        }

        return view;
    }
    fun setListenerInfant(infantListener: InfantListener){
        this.infantListener = infantListener
    }
}// Required empty public constructor