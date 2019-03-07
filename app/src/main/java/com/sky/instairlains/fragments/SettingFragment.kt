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
import com.sky.instairlains.viewModels.FlyViewModel
import kotlinx.android.synthetic.main.fragment_settings.*
import timber.log.Timber.i

class SettingFragment : Fragment() {

    private lateinit var adapter : AirlainsAdapter
    private lateinit var flyViewModel: FlyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        flyViewModel = ViewModelProviders.of(this).get(FlyViewModel::class.java)
        adapter = AirlainsAdapter(view.context)
        setRecyclerAirlains()
        flyViewModel.getFly()
        flyViewModel.success
            .observe(this, Observer {
              //  i(it.toString())
                i(it.size.toString())
                adapter.clear()
                adapter.addAll(it)
            })
        flyViewModel.error
            .observe(this, Observer {
                i(it)
                Toast.makeText(context,"ERRORE! Files not found!", Toast.LENGTH_SHORT).show()
            })
    }


    private fun setRecyclerAirlains() {
        recyclerAirlains.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        recyclerAirlains.adapter = adapter
    }


}