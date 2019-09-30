package com.ipay88.airasia.Activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ipay88.airasia.Constant
import com.ipay88.airasia.Country
import com.ipay88.airasia.Model.FlightModel
import com.ipay88.airasia.Model.Guest
import com.ipay88.airasia.R
import kotlinx.android.synthetic.main.activity_guest_details.*
import com.ipay88.airasia.ViewUtility.Animation
import kotlinx.android.synthetic.main.cart_header_layout.*
import java.util.*


class GuestDetailsActivity : AppCompatActivity() {
    lateinit var origin : Country
    lateinit var destination : Country
    lateinit var departDate : Calendar
    lateinit var returnDate : Calendar
    lateinit var guest : Guest
    lateinit var ticketOrigin : FlightModel
    lateinit var ticketDestination : FlightModel
    val ACTIVITY_RESULT_PAYMENT = 1

    var isOpen = false
    var gender = "F"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_details)
        tvScroll.setOnClickListener {
            if(isOpen) {
               // receipt.visibility= View.GONE

                isOpen=false

               Animation.SlideInFromTopToBottom(receipt,parentLayout)
            }

            else{
                //receipt.visibility= View.VISIBLE

                isOpen=true
                Animation.SlideOutFromBottomToTop(receipt,parentLayout)

                Log.d("sada", "b")
            }


        }

        chooseGender()
        init()

    }
    fun init(){
        origin = intent.getSerializableExtra(Constant.ORIGIN) as Country
        destination = intent.getSerializableExtra(Constant.DESTINATION) as Country
        departDate = intent.getSerializableExtra(Constant.DEPARTDATE) as Calendar
        returnDate = intent.getSerializableExtra(Constant.RETURNDATE) as Calendar
        guest = intent.getSerializableExtra(Constant.GUEST) as Guest
        ticketOrigin = intent.getSerializableExtra(Constant.TICKETORIGIN) as FlightModel
        ticketDestination = intent.getSerializableExtra(Constant.TICKETDESTINATION) as FlightModel

        tvOrigin1.setText(ticketOrigin.origin)
        tvDestination1.setText(ticketOrigin.destination)
        tvOriginCity1.setText(origin.City)
        tvOriginState1.setText(origin.Street)
        tvDestinationCity1.setText(destination.City)
        tvDestinationState1.setText(destination.Street)
        tvTicketPrice1.text=ticketOrigin.price.toString()


        tvOrigin2.setText(ticketDestination.origin)
        tvDepart2.setText(ticketDestination.destination)
        tvOriginCity2.setText(destination.City)
        tvOriginState2.setText(destination.Street)
        tvDestinationCity2.setText(origin.City)
        tvDestinationState2.setText(origin.Street)
        tvTicketPrice2.text=ticketDestination.price.toString()

        tvPrice.text=(ticketOrigin.price+ticketDestination.price).toString()

        btnSubmit.setOnClickListener {
            var intent = Intent(this@GuestDetailsActivity,WebViewActivity::class.java)
            startActivityForResult(intent,ACTIVITY_RESULT_PAYMENT)
        }
    }

    fun chooseGender(){

        tvMale.setOnClickListener {
            Log.d("sada","Click")
            gender="M"
            tvMale.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
            tvMale.setTextColor(resources.getColor(android.R.color.white))
            tvFemale.setBackgroundColor(resources.getColor(R.color.light_grey1))
            tvFemale.setTextColor(resources.getColor(R.color.lighter_grey2))

        }
        tvFemale.setOnClickListener {
            gender="F"
            tvFemale.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
            tvFemale.setTextColor(resources.getColor(android.R.color.white))
            tvMale.setBackgroundColor(resources.getColor(R.color.light_grey1))
            tvMale.setTextColor(resources.getColor(R.color.lighter_grey2))

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==ACTIVITY_RESULT_PAYMENT){
            if(resultCode== Activity.RESULT_OK){
                Toast.makeText(this@GuestDetailsActivity,data!!.getStringExtra("result"),Toast.LENGTH_SHORT)
            }
        }
    }
}
