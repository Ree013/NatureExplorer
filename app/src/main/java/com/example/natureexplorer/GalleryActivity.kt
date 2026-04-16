package com.example.natureexplorer

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)


        val toolbar: Toolbar = findViewById(R.id.galleryToolbar)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener { finish() }


        val title       = intent.getStringExtra("ITEM_TITLE")       ?: "Unknown"
        val description = intent.getStringExtra("ITEM_DESCRIPTION") ?: "No description"


        val imageResId  = intent.getIntExtra("ITEM_IMAGE", R.drawable.pandora2)


        findViewById<ImageView>(R.id.galleryImage).setImageResource(imageResId)
        findViewById<TextView>(R.id.galleryTitle).text = title
        findViewById<TextView>(R.id.galleryDescription).text = description


        findViewById<MaterialButton>(R.id.btnViewDetails).setOnClickListener {


            val intent = Intent(this, DetailsActivity::class.java)


            intent.putExtra("DETAIL_TITLE", title)
            intent.putExtra("DETAIL_DESCRIPTION", description)

            intent.putExtra("DETAIL_IMAGE", imageResId)

            startActivity(intent)
        }
    }
}