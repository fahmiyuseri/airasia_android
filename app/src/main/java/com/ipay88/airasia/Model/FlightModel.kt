package com.ipay88.airasia.Model

import java.io.Serializable
import java.util.*

class FlightModel : Serializable{

    var origin = ""
    var destination = ""
    var departTime = ""
    var arriveTime = ""
    var price = 0.0
    var currency = ""
    var date : Calendar? = null

    constructor(origin: String,destination : String, price : Double) {
        this.origin = origin
        this.price = price
        this.destination = destination
    }
}