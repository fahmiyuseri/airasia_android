package com.ipay88.airasia.Activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.fahmiyuseri.customcalendarlibrary.CalendarCustomView
import com.fahmiyuseri.customcalendarlibrary.EventsObject
import com.ipay88.airasia.Constant
import com.ipay88.airasia.R
import kotlinx.android.synthetic.main.activity_select_country_list.*
import kotlinx.android.synthetic.main.activity_select_date.*
import java.util.*

class SelectDateActivity : AppCompatActivity() {
    enum class DateType {
        DEPART, RETURN
    }
      var depart : Calendar? = null
    var returning : Calendar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_date)
        var cal = Calendar.getInstance(Locale.ENGLISH)
        var events = ArrayList<EventsObject>()
        events.add(EventsObject("84",cal.time))
        cal.add(Calendar.DAY_OF_MONTH,1)
        events.add(EventsObject("84",cal.time))
         cal.add(Calendar.DAY_OF_MONTH,1)
        events.add(EventsObject("84",cal.time))
         cal.add(Calendar.DAY_OF_MONTH,1)
        events.add(EventsObject("84",cal.time))
         cal.add(Calendar.DAY_OF_MONTH,1)
        events.add(EventsObject("84",cal.time))
         cal.add(Calendar.DAY_OF_MONTH,1)
        events.add(EventsObject("84",cal.time))
         cal.add(Calendar.DAY_OF_MONTH,1)
        events.add(EventsObject("84",cal.time))
         cal.add(Calendar.DAY_OF_MONTH,1)
        events.add(EventsObject("84",cal.time))
         cal.add(Calendar.DAY_OF_MONTH,1)
        events.add(EventsObject("84",cal.time))
         cal.add(Calendar.DAY_OF_MONTH,1)
        events.add(EventsObject("84",cal.time))
         cal.add(Calendar.DAY_OF_MONTH,1)
        events.add(EventsObject("84",cal.time))

        calendar.setEvents(events)

        calendar.setCalendarSelectedListener { Depart, Return ->
            depart = Depart
            returning = Return
    if(Return!=null)
        Log.d("sada","Depart :"+Depart.time.toString() +" untill "+ Return.time.toString())
    if(Depart!=null)
        Log.d("sada","Depart :"+Depart.time.toString())
}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_select_country, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_clear->{
                calendar.clearSelectedDate()
            }
            R.id.menu_submit->{
                intent.putExtra(Constant.DEPARTDATE,depart)
                    intent.putExtra(Constant.RETURNDATE,returning)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }



        }
        return super.onOptionsItemSelected(item)
    }

}
