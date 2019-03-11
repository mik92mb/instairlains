package com.sky.instairlains.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.instairlains.R
import com.sky.instairlains.adapters.AirlainsAdapter
import com.sky.instairlains.viewModels.SettingsViewModel
import kotlinx.android.synthetic.main.fragment_settings.*
import timber.log.Timber.i

class SettingFragment : Fragment() {

    private lateinit var adapter: AirlainsAdapter
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        adapter = AirlainsAdapter(view.context)
        setRecyclerAirlains()
        seCallGetFly(adapter)
    }

    private fun seCallGetFly(adapter: AirlainsAdapter) {
        settingsViewModel.getFly(adapter)
        settingsViewModel.success
            .observe(this, Observer {
                progressAirlains.visibility = View.GONE
           //     adapter.clear()
            //    adapter.addAll(it)
            })
        settingsViewModel.error.observe(this, Observer {
            progressAirlains.visibility = View.GONE
            i(it)
            Toast.makeText(context, "ERRORE! Files not found!", Toast.LENGTH_SHORT).show()
        })
    }

    private fun setRecyclerAirlains() {
        recyclerAirlains.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerAirlains.adapter = adapter
    }


}