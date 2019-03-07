package com.sky.instairlains.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sky.instairlains.BaseActivity
import com.sky.instairlains.R
import com.sky.instairlains.fragments.DashboardFragment
import com.sky.instairlains.fragments.HomeFragment
import com.sky.instairlains.fragments.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var active: Fragment
    private lateinit var homeFragment: HomeFragment
    private lateinit var settingFragment: SettingFragment
    private lateinit var dashboardFragment: DashboardFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragments()
        navigation.setOnNavigationItemSelectedListener(this)
    }

    private fun setFragments() {
        homeFragment = HomeFragment()
        dashboardFragment = DashboardFragment()
        settingFragment = SettingFragment()
        active = homeFragment
        supportFragmentManager.beginTransaction().add(R.id.main_container, settingFragment, "3").hide(settingFragment)
            .commit()
        supportFragmentManager.beginTransaction().add(R.id.main_container, dashboardFragment, "2").hide(dashboardFragment)
            .commit()
        supportFragmentManager.beginTransaction().add(R.id.main_container, homeFragment, "1").commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                openFragment(homeFragment, active)
                active = homeFragment
                return true
            }
            R.id.navigation_dashboard -> {
                openFragment(dashboardFragment, active)
                active = dashboardFragment
                return true
            }
            R.id.navigation_notifications -> {
                openFragment(settingFragment, active)
                active = settingFragment
                return true
            }
        }
        return false
    }


    private fun openFragment(fragment: Fragment, activeFragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .hide(activeFragment)
            .show(fragment)
            .commit()
        active = homeFragment
    }
}
