package com.kotlin.wirawan.icubetest.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kotlin.wirawan.icubetest.R
import com.kotlin.wirawan.icubetest.ui.places.PlacePickerActivity

class MainActivity : AppCompatActivity(), MainContract.View {

    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter()
        presenter?.attach(this)

        // Set button action listener
        val nextButton = findViewById(R.id.next_button) as Button
        nextButton.setOnClickListener {
            this.onSubmitButtonClick()
        }
    }

    override fun onSubmitButtonClick() {
        val intent = Intent(this, PlacePickerActivity::class.java)
        startActivity(intent)
    }
}
