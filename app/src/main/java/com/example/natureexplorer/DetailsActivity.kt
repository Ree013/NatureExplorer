package com.example.natureexplorer

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val toolbar: Toolbar = findViewById(R.id.detailsToolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }


        val title       = intent.getStringExtra("DETAIL_TITLE")       ?: "Unknown"
        val description = intent.getStringExtra("DETAIL_DESCRIPTION") ?: "No description"
        val funFact     = intent.getStringExtra("DETAIL_FUN_FACT")     ?: "No fun fact"
        val imageResId  = intent.getIntExtra("DETAIL_IMAGE", R.drawable.nature_image)


        findViewById<ImageView>(R.id.detailsImage).setImageResource(imageResId)
        findViewById<TextView>(R.id.detailsTitle).text = title
        findViewById<TextView>(R.id.detailsDescription).text = description



        findViewById<MaterialButton>(R.id.btnShare).setOnClickListener {

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out: $title")
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "I just learned about $title on Nature Explorer!\n\n$funFact"
            )

            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}