package com.example.jul21mvvmrickandmorty

import android.os.Bundle
import android.view.MenuItem
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

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.characterByIdResponseLiveData.observe(this) { response ->
            epoxyController.characterResponse = response
            if (response == null) {
                Toast.makeText(this@MainActivity, "Unsuccessful network call", Toast.LENGTH_SHORT)
                    .show()
                return@observe
            }
        }

        val id = intent.getIntExtra(Constants.INTENT_EXTRA_CHARACTER_ID, 1)
        viewModel.refreshByCharacter(id)
        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}