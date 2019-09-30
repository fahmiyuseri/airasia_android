package com.ipay88.airasia.Activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.ipay88.airasia.Adapter.CountryAdapter
import com.ipay88.airasia.Constant
import com.ipay88.airasia.Constant.COUNTRY_TYPE
import com.ipay88.airasia.Country
import com.ipay88.airasia.CountryJSON
import com.ipay88.airasia.Model.RecentSearchModel
import com.ipay88.airasia.R
import kotlinx.android.synthetic.main.activity_select_country_list.*
enum class CountryType {
    ORIGIN, DESTINATION
}
class SelectCountryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_country_list)
        var countryType = intent.getSerializableExtra(COUNTRY_TYPE) as CountryType
        if(countryType== CountryType.ORIGIN){

        }
        val mLayoutManagerBestDeals = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvCountry.setLayoutManager(mLayoutManagerBestDeals)
        var adapter = CountryAdapter(this,CountryJSON.CountryList(),countryType)
        rvCountry.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_select_country, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_clear->{
                tvOrigin.setText("")
                tvDestination.setText("")
            }
            R.id.menu_submit->{
                intent.putExtra(Constant.ORIGIN,tvOrigin.tag as Country)
                intent.putExtra(Constant.DESTINATION,tvDestination.tag as Country)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }



        }
        return super.onOptionsItemSelected(item)
    }
}
