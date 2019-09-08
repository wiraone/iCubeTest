package com.kotlin.wirawan.icubetest.ui.places

import com.kotlin.wirawan.icubetest.api.ApiServicesInterface
import com.kotlin.wirawan.icubetest.models.Place
import com.kotlin.wirawan.icubetest.models.PlaceData
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlacePickerPresenter: PlacePickerContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServicesInterface = ApiServicesInterface.create()
    private lateinit var view: PlacePickerContract.View

    override fun subscribe() {}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: PlacePickerContract.View) {
        this.view = view
    }

    override fun loadPlaceList() {
        var subscribe = api.getPlaceList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data: PlaceData? ->
                view.showProgress(false)
                view.loadPlaceListSuccess(data!!.place)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

}