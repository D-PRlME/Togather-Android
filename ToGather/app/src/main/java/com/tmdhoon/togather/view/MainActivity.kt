package com.tmdhoon.togather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivityMainBinding
import com.tmdhoon.togather.view.fragment.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavigation()
        initBottomNavigationPost()
    }

    private fun initBottomNavigationPost() {
        binding.bnMainBottomNavigationPost.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_post -> PostFragment().show(supportFragmentManager, PostFragment().tag)
            }
            true
        }
    }

    private fun initBottomNavigation() {
        changeFragment(HomeFragment())
        binding.bnMainBottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> changeFragment(HomeFragment())
                R.id.menu_chat -> changeFragment(ChatFragment())
                R.id.menu_search -> changeFragment(SearchFragment())
                R.id.menu_mypage -> changeFragment(MyInfoFragment())
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment)
            .commitAllowingStateLoss()
    }
}