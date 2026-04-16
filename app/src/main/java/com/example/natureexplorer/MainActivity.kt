package com.example.natureexplorer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)


        val natureList = listOf(
            NatureItem(
                title = " San Juan de Gaztelugatxe",
                description = "An islet on the Basque coast in Northern Spain. The iconic winding staircase leading to Daenerys' ancestral home is located here. Other scenes for the location were filmed at nearby beaches, specifically Itzurun Beach in Zumaia and Muriola Beach in Barrika.",
                imageResId = R.drawable.got
            ),
            NatureItem(
                title = "Pandora mountains",
                description = "iconic floating cliffs in James Cameron's Avatar, inspired by the real-world quartz-sandstone pillars of Zhangjiajie National Forest Park in China's Hunan Province.",
                imageResId = R.drawable.pandora1
            ),
            NatureItem(
                title = " Pandora's Eastern Sea",
                description = "The Metkayina are an oceanic Na'vi clan located on Pandora's reefs on the Eastern Sea. Of the over 50 reef clans, the Metkayina are the largest. The clan's olo'eyktan is Tonowari, and its tsahìk was once Ronal, and is now presumably Tsireya. The clan's main village is Awa'atlu.",
                imageResId = R.drawable.images
            ),
            NatureItem(
                title = " Dark Hedges ",
                description = "a 250-year-old avenue of beech trees that represented the King's Road",
                imageResId = R.drawable.got_forest
            ),
            NatureItem(
                title = "Kirkjufell",
                description = "Iconic arrowhead mountain featured in Seasons 6 and 7 of GOT.",
                imageResId = R.drawable.gotwater2
            )
        )




        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(this)


        val adapter = NatureAdapter(this, natureList) { clickedItem ->


            val index = natureList.indexOf(clickedItem)



            val intent = Intent(this, GalleryActivity::class.java)


            intent.putExtra("ITEM_TITLE",       clickedItem.title)
            intent.putExtra("ITEM_DESCRIPTION", clickedItem.description)

            intent.putExtra("ITEM_IMAGE",       clickedItem.imageResId)

            startActivity(intent) // Open GalleryActivity
        }

        recyclerView.adapter = adapter
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {


            R.id.nav_home -> {
                Toast.makeText(this, "You are already on Home", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_gallery -> {

                val intent = Intent(this, GalleryActivity::class.java)
                intent.putExtra("ITEM_TITLE",       "Gallery")
                intent.putExtra("ITEM_DESCRIPTION", "Browse all nature photos in this collection.")
                intent.putExtra("ITEM_FUN_FACT",    "Photography helps us notice beauty we otherwise walk past.")
                intent.putExtra("ITEM_IMAGE",       R.drawable.nature_image)
                startActivity(intent)
            }
            R.id.nav_favorites -> {
                Toast.makeText(this, " Favorites coming soon!", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_settings -> {
                Toast.makeText(this, "Settings coming soon!", Toast.LENGTH_SHORT).show()
            }


            R.id.nav_website -> {
                val websiteIntent = Intent(Intent.ACTION_VIEW)
                websiteIntent.data = Uri.parse("https://www.wwf.org.za")
                startActivity(websiteIntent)
            }


            R.id.nav_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out Nature Explorer!")
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "I've been using Nature Explorer to discover amazing nature facts. Check it out!"
                )
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    }
