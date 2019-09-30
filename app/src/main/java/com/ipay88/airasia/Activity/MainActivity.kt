package com.ipay88.airasia.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ipay88.airasia.Adapter.ActivitesAdapter
import com.ipay88.airasia.Adapter.BestDealAdapter
import com.ipay88.airasia.Adapter.RecentSearchAdapter
import com.ipay88.airasia.Model.BestDealModel
import com.ipay88.airasia.Model.RecentSearchModel
import com.ipay88.airasia.R
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var recentSearchs = ArrayList<RecentSearchModel>()
        var bestDeals = ArrayList<BestDealModel>()
        recentSearchs.add(RecentSearchModel("Male (MLE)"))
        recentSearchs.add(RecentSearchModel("Sydney (SYD)"))
        recentSearchs.add(RecentSearchModel("Bali (DPS)"))
        recentSearchs.add(RecentSearchModel("Kota Bharu (KBR)"))
        recentSearchs.add(RecentSearchModel("Johor Bahru (JHB)"))

        bestDeals.add(BestDealModel("Alor Setar","Local Food - Nature and Sightseeing - Leisure", "MYR 54.30"))
        bestDeals.add(BestDealModel("Alor Setar","Local Food - Nature and Sightseeing - Leisure", "MYR 54.30"))
        bestDeals.add(BestDealModel("Alor Setar","Local Food - Nature and Sightseeing - Leisure", "MYR 54.30"))
        bestDeals.add(BestDealModel("Alor Setar","Local Food - Nature and Sightseeing - Leisure", "MYR 54.30"))
        bestDeals.add(BestDealModel("Alor Setar","Local Food - Nature and Sightseeing - Leisure", "MYR 54.30"))

        val mLayoutManagerDate = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycleList.setLayoutManager(mLayoutManagerDate)
        var recentAdapter = RecentSearchAdapter(this,recentSearchs)
        recycleList.adapter=recentAdapter
        recycleList.visibility=View.VISIBLE

        val mLayoutManagerBestDeals = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvBestDeals.setLayoutManager(mLayoutManagerBestDeals)
        var bestAdapter = BestDealAdapter(this,bestDeals)
        rvBestDeals.adapter=bestAdapter
        rvBestDeals.visibility=View.VISIBLE



        val mLayoutManagerActivities = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvActivities.setLayoutManager(mLayoutManagerActivities)
        var activitiesAdapter = ActivitesAdapter(this,bestDeals)
        rvActivities.adapter=activitiesAdapter

        btnSearchFlight.setOnClickListener {
            var intent = Intent(this@MainActivity,MapActivity::class.java)
            startActivity(intent)
        }

    }
}
