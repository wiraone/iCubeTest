package com.kotlin.wirawan.icubetest.api

import com.kotlin.wirawan.icubetest.models.Place
import com.kotlin.wirawan.icubetest.models.PlaceData
import com.kotlin.wirawan.icubetest.utils.Constant
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServicesInterface {

    @GET("places")
    fun getPlaceList(): Observable<PlaceData>

    companion object Factory {
        fun create(): ApiServicesInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()

            return retrofit.create(ApiServicesInterface::class.java)
        }
    }
}