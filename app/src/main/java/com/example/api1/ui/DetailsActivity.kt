package com.example.api1.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.api1.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)

        val imgTheme =
            findViewById<ImageView>(R.id.imgTheme)

        val txtThemeName =
            findViewById<TextView>(R.id.txtThemeName)

        val txtLocation =
            findViewById<TextView>(R.id.txtLocation)

        val imageUrl =
            intent.getStringExtra("image")

        val themeName =
            intent.getStringExtra("name")

        val location =
            intent.getStringExtra("location")

        txtThemeName.text =
            themeName

        txtLocation.text =
            "Location:\n$location"

        Glide.with(this)
            .load(imageUrl)
            .into(imgTheme)
    }
}