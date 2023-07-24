package com.example.jul21mvvmrickandmorty

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.characterNameTextView)
        val statusTextView = findViewById<TextView>(R.id.characterStatusTextView)
        val speciesTextView = findViewById<TextView>(R.id.characterSpeciesTextView)
        val originTextView = findViewById<TextView>(R.id.characterOriginTextView)
        val characterImageView = findViewById<ImageView>(R.id.characterImageView)
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val rickAppCompatActivity : RickAndMortyService = retrofit.create(RickAndMortyService::class.java)

        rickAppCompatActivity.getCharacterById(2).enqueue(object : Callback<GetCharacterByIdResponse>{
            override fun onResponse(call: Call<GetCharacterByIdResponse>, response: Response<GetCharacterByIdResponse>) {
                Log.i("MainActivity", response.toString())
                if (!response.isSuccessful){
                    Toast.makeText(this@MainActivity, "Unsuccessful network call", Toast.LENGTH_SHORT).show()
                    return
                }
                val body = response.body()!!

                nameTextView.text = body.name
                statusTextView.text = body.status
                speciesTextView.text = body.species
                originTextView.text =  body.origin.name

                val url = body.image
                Picasso.get()
                    .load(url)
                    .into(characterImageView)

            }

            override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                Log.i("MainActivity", t.message ?: "Null message")
            }
        })
    }
}