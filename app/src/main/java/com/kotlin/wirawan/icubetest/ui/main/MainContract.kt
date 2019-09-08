package com.kotlin.wirawan.icubetest.ui.main

import com.kotlin.wirawan.icubetest.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun onSubmitButtonClick()
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
        fun validateUsername()
        fun validateEmail()
    }
}