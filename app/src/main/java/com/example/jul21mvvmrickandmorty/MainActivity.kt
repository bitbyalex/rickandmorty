package com.example.jul21mvvmrickandmorty

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {

    val viewModel :  ShareViewModel by lazy {
        ViewModelProvider(this).get(ShareViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.characterNameTextView)
        val statusTextView = findViewById<TextView>(R.id.characterStatusTextView)
        val speciesTextView = findViewById<TextView>(R.id.characterSpeciesTextView)
        val originTextView = findViewById<TextView>(R.id.characterOriginTextView)
        val characterImageView = findViewById<ImageView>(R.id.characterImageView)

        viewModel.refreshByCharacter(54)
        viewModel.characterByIdResponseLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(this@MainActivity, "Unsuccessful network call", Toast.LENGTH_SHORT)
                    .show()
                return@observe
            }
            nameTextView.text = response.name
            statusTextView.text = response.status
            speciesTextView.text = response.species
            originTextView.text = response.origin.name

            Picasso.get()
                .load(response.image)
                .into(characterImageView)

        }
    }
}