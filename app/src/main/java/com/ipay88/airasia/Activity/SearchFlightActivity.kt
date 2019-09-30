package com.ipay88.airasia.Activity

import android.animation.LayoutTransition
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.applandeo.materialcalendarview.adapters.CalendarPageAdapter
import com.ipay88.airasia.Activity.ui.main.SectionsPagerAdapter
import com.ipay88.airasia.Adapter.CalendarPagerAdapter
import com.ipay88.airasia.Adapter.FlightListAdapter
import com.ipay88.airasia.Adapter.ListFlightPagerAdapter
import com.ipay88.airasia.Adapter.RecentSearchAdapter
import com.ipay88.airasia.Constant
import com.ipay88.airasia.Country
import com.ipay88.airasia.Fragment.BlankFragment
import com.ipay88.airasia.Model.FlightModel
import com.ipay88.airasia.Model.Guest
import com.ipay88.airasia.R
import com.ipay88.airasia.ViewUtility.HeightWrappingViewPager
import com.ipay88.airasia.ViewUtility.ViewPagerWithScrollListener
import kotlinx.android.synthetic.main.activity_search_flight.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SearchFlightActivity : AppCompatActivity(), ViewPager.OnPageChangeListener,BlankFragment.OnFragmentInteractionListener,
    ViewPagerWithScrollListener.CustomOnScrollChangeListener,HeightWrappingViewPager.CustomOnScrollChangeListener {

    enum class FlightType {
        DEPART, RETURN
    }
    lateinit var calendar: Calendar
    var current_week = 0
    var listDayDateModel = ArrayList<String>()
    lateinit var adapter : CalendarPagerAdapter
    lateinit var sectionsPagerAdapter : SectionsPagerAdapter
    lateinit var flightPagerAdapter : ListFlightPagerAdapter

    lateinit var origin : Country
    lateinit var destination : Country
    lateinit var departDate : Calendar
    lateinit var returnDate : Calendar
    lateinit var guest : Guest
    lateinit var selectedFlight : ArrayList<Wrappter>
    var ticketOrigin : FlightModel? = null
    var ticketDestination : FlightModel? = null
    var totalprice = 0.0
    var flightType : FlightType? = null

    var listFlight = ArrayList<FlightModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_flight)
        selectedFlight = ArrayList()
        origin = intent.getSerializableExtra(Constant.ORIGIN) as Country
        destination = intent.getSerializableExtra(Constant.DESTINATION) as Country
        departDate = intent.getSerializableExtra(Constant.DEPARTDATE) as Calendar
        returnDate = intent.getSerializableExtra(Constant.RETURNDATE) as Calendar
        guest = intent.getSerializableExtra(Constant.GUEST) as Guest
        calendar = Calendar.getInstance()


        if(intent.getSerializableExtra(Constant.FLIGHTYPE)!=null) {
            flightType = intent.getSerializableExtra(Constant.FLIGHTYPE) as FlightType
            ticketOrigin = intent.getSerializableExtra(Constant.TICKETORIGIN) as FlightModel
            totalprice = ticketOrigin!!.price.toDouble()
            calendar = returnDate.clone() as Calendar
            Log.d("sadaaaa",ticketOrigin!!.price.toString())
        }
        else {
            flightType = FlightType.DEPART
            calendar = departDate.clone() as Calendar
            Log.d("sadaaaa",departDate.time.toString())

        }
        tvPrice.setText(totalprice.toString().split("\\.")[0])
        current_week = calendar.get(Calendar.WEEK_OF_YEAR)
        flightPagerAdapter = ListFlightPagerAdapter(this@SearchFlightActivity, listDayDateModel,flightType,origin.CityCode,destination.CityCode)
        flightPagerAdapter.setListener { flightModel, pos ->
            Log.d("sadaaaaaa", pos.toString())
            if(flightType==FlightType.DEPART){
                ticketOrigin = flightModel
                if(ticketOrigin!=null){

                    ticketOriginSelected(list_view_pager.currentItem,ticketOrigin!!)

                    }

            }
            else{
                ticketDestination = flightModel
                if(ticketDestination!=null){
               //     tvPrice.setText((ticketOrigin!!.price+ticketDestination!!.price).toString());
                    ticketOriginSelected(list_view_pager.currentItem,ticketDestination!!)

                    //    tvPrice2.setText("."+(ticketOrigin!!.price+ticketDestination!!.price).split("\\.")[1]);
                }
            }
        /*    var intent = Intent(this@SearchFlightActivity,SearchFlightActivity::class.java)
            intent.putExtra(Constant.FLIGHTYPE,FlightType.RETURN)
            intent.putExtra(Constant.ORIGIN,origin)
            intent.putExtra(Constant.DESTINATION,destination)
            intent.putExtra(Constant.DEPARTDATE,departDate)
            intent.putExtra(Constant.RETURNDATE,returnDate)
            intent.putExtra(Constant.GUEST,guest)
            intent.putExtra(Constant.TICKET,flightModel)
            startActivity(intent)*/
        }
        list_view_pager.offscreenPageLimit=100
        list_view_pager.adapter = flightPagerAdapter

         adapter = CalendarPagerAdapter(this, listDayDateModel)
        calendar_view_pager.adapter = adapter
        list_view_pager.addOnPageChangeListener(this)
        list_view_pager.setCustomScrollChangeListener(this)
        calendar_view_pager.setCustomScrollChangeListener(this)
        calendar_view_pager.setPagingEnabled(false)
        calendar.add(Calendar.DAY_OF_YEAR, -14)
        nextweek()
        nextweek()
        nextweek()
        nextweek()
        nextweek()
        adapter.setListdate(listDayDateModel)
        flightPagerAdapter.setListdate(listDayDateModel)

        if(flightType==FlightType.DEPART) {
            list_view_pager.setCurrentItem(positionDate(departDate), true)
            tvFlightType.text = "Depart"
            tvOrigin.text = origin.CityCode
            tvDestination.text = destination.CityCode

        }
        else{
            list_view_pager.setCurrentItem(positionDate(returnDate), true)
        }

 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            (layoutout.getLayoutTransition()
                .enableTransitionType(LayoutTransition.CHANGING))
        }

        btnSubmit.setOnClickListener {
            if(flightType== SearchFlightActivity.FlightType.DEPART) {
                var intent = Intent(this@SearchFlightActivity,SearchFlightActivity::class.java)
                intent.putExtra(Constant.FLIGHTYPE,FlightType.RETURN)
                intent.putExtra(Constant.ORIGIN,origin)
                intent.putExtra(Constant.DESTINATION,destination)
                intent.putExtra(Constant.DEPARTDATE,departDate)
                intent.putExtra(Constant.RETURNDATE,returnDate)
                intent.putExtra(Constant.GUEST,guest)
                intent.putExtra(Constant.TICKETORIGIN,ticketOrigin)
                startActivity(intent)
            }
            else{
                var intent =  Intent(this@SearchFlightActivity, GuestDetailsActivity::class.java)
                intent.putExtra(Constant.FLIGHTYPE, SearchFlightActivity.FlightType.RETURN);
                intent.putExtra(Constant.ORIGIN,origin)
                intent.putExtra(Constant.DESTINATION,destination)
                intent.putExtra(Constant.DEPARTDATE,departDate)
                intent.putExtra(Constant.RETURNDATE,returnDate)
                intent.putExtra(Constant.GUEST,guest)
                intent.putExtra(Constant.TICKETORIGIN,ticketOrigin)
                intent.putExtra(Constant.TICKETDESTINATION,ticketDestination)
                startActivity(intent);
            }
        }


    }
    override fun onScrollChangeHeight(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        Log.d("calendarScroll",scrollX.toString())
    }

    override fun onScrollChange(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        Log.d("listScroll",scrollX.toString())

        calendar_view_pager.scrollX= (scrollX/3.0379746835).toInt()

    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    //VIEW PAGER LISTENER
    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }


    override fun onPageSelected(p0: Int) {
        calendar_view_pager.setCurrentItem(p0-1, false)
        var isGotTicket = false
        for(ticket in selectedFlight){
            Log.d("sada","posisi "+p0+" "+ticket.pos + " "+ ticket.ticket)
            Log.d("sada",""+ticket.pos + " "+ ticket.ticket)
            if(ticket.pos==p0){
                tvPrice.setText(ticket.ticket!!.price.toString());
                isGotTicket=true

            }

        }
        if(isGotTicket){

            btnSubmit.visibility=View.VISIBLE

        }
        else{
            tvPrice.setText("");
            btnSubmit.visibility=View.INVISIBLE


        }


//        var strDate =  listDayDateModel.get(p0 + 1)





    }

    fun nextweek() {


        //    Toast.makeText(getContext(),"Current Week is"+current_week +"Start Day is",Toast.LENGTH_SHORT).show();


        // get the starting and ending date
        // Set the calendar to sunday of the current week
      //  calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        println("Current week = " + Calendar.DAY_OF_WEEK)

        // Print dates of the current week starting on Sunday
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val df2 = SimpleDateFormat("dd-EEE", Locale.getDefault())
        val df3 = SimpleDateFormat("EEE dd MMM", Locale.getDefault())
        val startDate = ""
        val endDate = ""

        //  startDate = df.format(calendar.getTime());
        //  calendar.add(Calendar.DATE, 6);
        //  endDate = df.format(calendar.getTime());
        for (i in 0..6) {
            println("date  = " + df.format(calendar.getTime()))
            listDayDateModel.add(
                df3.format(calendar.getTime())

                )

            calendar.add(Calendar.DATE, 1)

        }
        println("Start Date = $startDate")
        println("End Date = $endDate")
        current_week++
        // dayAdapter.notifyDataSetChanged();


    }
    fun positionDate(date  : Calendar) : Int{
        var pos = 0
        var y=0
        val df3 = SimpleDateFormat("EEE dd MMM", Locale.getDefault())
        Log.d("posdate",df3.format(date.time))
        Log.d("posdateORIGINAl",date.time.toString())

        for (i in  listDayDateModel){

          if(i.contains(df3.format(date.time))){
             pos = y
              Log.d("posdate",i)
          }
          y++
      }
        return  pos

    }

    fun ticketOriginSelected(pos : Int,ticketOrigin : FlightModel){
        tvPrice.setText(ticketOrigin!!.price.toString());
        var i=0
        var selected = -1
        for(ticket in selectedFlight!!){
            Log.d("sada","aahah "+i)
            if(ticket.pos==list_view_pager.currentItem){
                selected=i
            }
            i++
        }
        if(selected>=0){
        selectedFlight.removeAt(selected)
        }
        selectedFlight!!.add(Wrappter(pos,ticketOrigin))

        if(flightType== SearchFlightActivity.FlightType.DEPART) {
            this.ticketOrigin=ticketOrigin
        }
        else{
            ticketDestination = ticketOrigin
        }

        }

    fun getSelected(): Int{
        var i=0
        var selected = -1
        for(ticket in selectedFlight!!){
            Log.d("sada","aahah "+i)
            if(ticket.pos==list_view_pager.currentItem){
               selected = i
            }
            i++
        }
        return selected
    }
}

class Wrappter {

    var pos = 0
    var ticket : FlightModel? = null

    constructor(pos: Int, ticket: FlightModel?) {
        this.pos = pos
        this.ticket = ticket
    }
}
