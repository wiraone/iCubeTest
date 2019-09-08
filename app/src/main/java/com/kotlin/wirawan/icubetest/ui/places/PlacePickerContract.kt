package com.kotlin.wirawan.icubetest.ui.places

import com.kotlin.wirawan.icubetest.models.Place
import com.kotlin.wirawan.icubetest.ui.base.BaseContract

interface PlacePickerContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadPlaceListSuccess(list: List<Place>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadPlaceList()
    }
}