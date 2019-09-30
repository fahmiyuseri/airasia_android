package com.ipay88.airasia.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.*
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.CardView
import android.util.Log
import android.widget.LinearLayout
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import com.ipay88.airasia.BottomSheetFragment.InfantSheetFragment
import com.ipay88.airasia.Constant
import com.ipay88.airasia.Constant.COUNTRY_TYPE
import com.ipay88.airasia.Constant.DATE_TYPE
import com.ipay88.airasia.CountryJSON.CountryList
import com.ipay88.airasia.Country
import com.ipay88.airasia.Model.Guest
import com.ipay88.airasia.R
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.infant_bottomsheet.*
import java.text.SimpleDateFormat


class MapActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    val SELECT_ORIGIN_ACTION = 1
    val SELECT_DATE = 2
    var line : Polyline? = null
    var marker_plane : Marker? = null
    var guest : Guest = Guest()
    private lateinit var mMap: GoogleMap
    var origin : Country? =  null
    var destination : Country? =  null
    var departDate : Calendar? =  null
    var returnDate : Calendar? =  null


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap




        generateMarker()


          }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        guest.adult=1
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        init()

    }

    fun init(){
        btnSearchFlight.isEnabled=false
        btnSearchFlight.setBackgroundColor(resources.getColor(R.color.light_grey1))

        tvOrigin.setOnClickListener {
            var intent = Intent(this@MapActivity,SelectCountryListActivity::class.java)
            intent.putExtra(COUNTRY_TYPE,CountryType.ORIGIN)
            startActivityForResult(intent,SELECT_ORIGIN_ACTION)
        }
        tvDepartDate.setOnClickListener {
            var intent = Intent(this@MapActivity,SelectDateActivity::class.java)
            intent.putExtra(DATE_TYPE, SelectDateActivity.DateType.DEPART)
            startActivityForResult(intent,SELECT_DATE)
        }
        tvAdults.setOnClickListener {
            val bottomSheetFragment = InfantSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.getTag())
            bottomSheetFragment.setListenerInfant(object : InfantSheetFragment.InfantListener{
                override fun onChooseInfant(adult: Int, kids: Int, infants: Int) {
                    tvAdults.text=adult.toString()
                    tvKids.text=kids.toString()
                    tvInfants.text=infants.toString()
                    guest.adult=adult
                    guest.kids=kids
                    guest.infant = infants

                }

            })

        }
        btnSearchFlight.setOnClickListener {
            var intent = Intent(this@MapActivity,SearchFlightActivity::class.java)
            intent.putExtra(Constant.ORIGIN,origin)
            intent.putExtra(Constant.DESTINATION,destination)
            intent.putExtra(Constant.DEPARTDATE,departDate)
            intent.putExtra(Constant.RETURNDATE,returnDate)
            intent.putExtra(Constant.GUEST,guest)
            startActivity(intent)
        }
    }

    fun calculateBearing(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Float {
        val sourceLatLng = LatLng(lat1, lng1)
        val destinationLatLng = LatLng(lat2, lng2)
        return SphericalUtil.computeHeading(sourceLatLng, destinationLatLng).toFloat()
    }
    fun generateMarker(){
        for (country in CountryList()){
            val latlng = LatLng(country.Lat, country.lgt)
            var maker = mMap.addMarker(MarkerOptions().position(latlng).title(country.City))
            maker.tag = country


        }
        val latlng = LatLng(CountryList().get(0).Lat, CountryList().get(0).lgt)
        mMap.setOnMarkerClickListener(this)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,5f))

    }
    override fun onMarkerClick(marker: Marker?): Boolean {
        if(line!=null) {
            line!!.remove();
        }
        if(marker_plane!=null){
            marker_plane!!.remove()
        }
        val height = 80
        val width = 60
        val bitmapdraw = resources.getDrawable(R.drawable.plane) as BitmapDrawable
        val b = bitmapdraw.bitmap
        val smallMarker = Bitmap.createScaledBitmap(b, width, height, false)
        val plane_icon = BitmapDescriptorFactory.fromBitmap(smallMarker)

        var country = marker!!.tag as Country
        if(origin==null){
            origin = country
            tvOrigin.setText(origin!!.City)


// Move the camera instantly to Sydney with a zoom of 15.
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 15f))

// Zoom in, animating the camera.
            mMap.animateCamera(CameraUpdateFactory.zoomIn())

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(1f), 2000, null)


// Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
            val cameraPosition = CameraPosition.Builder()
                .target(marker.position)      // Sets the center of the map to Mountain View
                .zoom(1f)                   // Sets the zoom
                .bearing(90f)                // Sets the orientation of the camera to east
                .tilt(30f)                   // Sets the tilt of the camera to 30 degrees
                .build()                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        //    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 15f))

        }
        else{
            destination = country
            tvDestination.setText(destination!!.City)
            val bounds = LatLngBounds.builder().include( LatLng(origin!!.Lat, origin!!.lgt)).include( LatLng(destination!!.Lat, destination!!.lgt)).build()
           marker_plane = mMap.addMarker(MarkerOptions().position(bounds.center).title("Marker in center").icon(plane_icon).rotation(calculateBearing(origin!!.Lat,origin!!.lgt,destination!!.Lat,destination!!.lgt)))
            val dash =  Dash(20.0f);
            val gap =  Gap(20.0f);
            var PATTERN_POLYGON_ALPHA = Arrays.asList(gap, dash)


             line = mMap.addPolyline(
                PolylineOptions()
                    .add(LatLng(origin!!.Lat, origin!!.lgt), LatLng(destination!!.Lat, destination!!.lgt))
                    .width(5f)
                    .color(Color.BLACK)
                    .pattern(PATTERN_POLYGON_ALPHA)
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
        }
        Log.d("sada",country.City)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==SELECT_ORIGIN_ACTION){
            if(resultCode== Activity.RESULT_OK){
                origin = data!!.getSerializableExtra(Constant.ORIGIN) as Country
                destination = data!!.getSerializableExtra(Constant.DESTINATION) as Country
                tvOrigin.text = origin!!.City
                tvDestination.text = destination!!.City
                markDestination()
                if(departDate!=null&&returnDate!=null){
                    btnSearchFlight.isEnabled=true
                    btnSearchFlight.setBackgroundColor(resources.getColor(R.color.green500))

                }

            }
        }else if(requestCode==SELECT_DATE){
            if(resultCode== Activity.RESULT_OK){
                val df = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                 departDate  = data!!.getSerializableExtra(Constant.DEPARTDATE) as Calendar
                 returnDate  = data!!.getSerializableExtra(Constant.RETURNDATE) as Calendar
                tvDepartDate.text = df.format(departDate!!.time)
                tvReturnDate.text = df.format(returnDate!!.time)
                if(origin!=null&&destination!=null){
                    btnSearchFlight.isEnabled=true
                    btnSearchFlight.setBackgroundColor(resources.getColor(R.color.green500))

                }
            }
        }
    }

    fun markDestination(){
        val height = 80
        val width = 60
        val bitmapdraw = resources.getDrawable(R.drawable.plane) as BitmapDrawable
        val b = bitmapdraw.bitmap
        val smallMarker = Bitmap.createScaledBitmap(b, width, height, false)
        val plane_icon = BitmapDescriptorFactory.fromBitmap(smallMarker)
        val bounds = LatLngBounds.builder().include( LatLng(origin!!.Lat, origin!!.lgt)).include( LatLng(destination!!.Lat, destination!!.lgt)).build()
        marker_plane = mMap.addMarker(MarkerOptions().position(bounds.center).title("Marker in center").icon(plane_icon).rotation(calculateBearing(origin!!.Lat,origin!!.lgt,destination!!.Lat,destination!!.lgt)))
        val dash =  Dash(20.0f);
        val gap =  Gap(20.0f);
        var PATTERN_POLYGON_ALPHA = Arrays.asList(gap, dash)


        line = mMap.addPolyline(
            PolylineOptions()
                .add(LatLng(origin!!.Lat, origin!!.lgt), LatLng(destination!!.Lat, destination!!.lgt))
                .width(5f)
                .color(Color.BLACK)
                .pattern(PATTERN_POLYGON_ALPHA)
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
    }
}
