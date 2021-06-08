package com.questronix.danica.codereviewloginapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.questronix.danica.codereviewloginapp.R
import com.questronix.danica.codereviewloginapp.ui.fragment.dashboard.accounts.AccountsFragment
import com.questronix.danica.codereviewloginapp.ui.fragment.dashboard.alert.AlertFragment
import com.questronix.danica.codereviewloginapp.ui.fragment.dashboard.approval.ApprovalFragment
import com.questronix.danica.codereviewloginapp.ui.fragment.dashboard.settings.SettingsFragment
import com.questronix.danica.codereviewloginapp.ui.fragment.dashboard.transact.TransactFragment

class BottomNav : AppCompatActivity(){
    private var actionBar: ActionBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_bottom_nav)
        actionBar = supportActionBar
        actionBar?.hide()
        val navView: BottomNavigationView = findViewById(R.id.navigation)

        replaceFragment(AccountsFragment())
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.account -> {
                item.isChecked = true
                replaceFragment(AccountsFragment())
            }
            R.id.transact -> {
                item.isChecked = true
                replaceFragment(TransactFragment())
                 }
            R.id.approval -> {
                item.isChecked = true
                replaceFragment(ApprovalFragment())
                 }
            R.id.alert -> {
                item.isChecked = true
                replaceFragment(AlertFragment())
                 }
            R.id.setting -> {
                item.isChecked = true
                replaceFragment(SettingsFragment())
                }
        }
        false
    }

    fun replaceFragment(fragment: Fragment){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_container,fragment,fragment.javaClass.simpleName)
        transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commit()
    }


    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 1) supportFragmentManager.popBackStack()
    }
}