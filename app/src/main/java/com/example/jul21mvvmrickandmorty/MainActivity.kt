package com.example.jul21mvvmrickandmorty

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView


class MainActivity : AppCompatActivity() {

    private val viewModel :  ShareViewModel by lazy {
        ViewModelProvider(this)[ShareViewModel::class.java]
    }

    private val epoxyController = CharacterDetailsEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.characterByIdResponseLiveData.observe(this) { response ->
            epoxyController.characterResponse = response
            if (response == null) {
                Toast.makeText(this@MainActivity, "Unsuccessful network call", Toast.LENGTH_SHORT)
                    .show()
                return@observe
            }
        }

        viewModel.refreshByCharacter(54)
        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}