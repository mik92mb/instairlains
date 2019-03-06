package com.sky.instairlains.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.sky.instairlains.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),  BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return true
            }
        }
        return false
    }
}
