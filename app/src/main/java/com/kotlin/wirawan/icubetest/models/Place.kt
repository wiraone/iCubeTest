package com.kotlin.wirawan.icubetest.models

data class PlaceData(val places: List<Place>)
data class Place(val id: Int, val name: String, val type: String, val price: Int, val latitude: String, val longitude: String)

/**
 * "id": 1,
    "name": "Noms Kopi",
    "type": "cafe",
    "latitude": "-6.9778288",/L;LLPL,LM
    "longitude":"110.4470717,17"
 */