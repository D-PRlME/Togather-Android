package com.tmdhoon.togather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivityMainBinding
import com.tmdhoon.togather.view.fragment.ChatFragment
import com.tmdhoon.togather.view.fragment.HomeFragment
import com.tmdhoon.togather.view.fragment.MyPageFragment
import com.tmdhoon.togather.view.fragment.SearchFragment

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val homeFragment: HomeFragment by lazy {
        HomeFragment.newInstance()
    }

    private val chatFragment: ChatFragment by lazy {
        ChatFragment.newInstance()
    }

    private val searchFragment: SearchFragment by lazy {
        SearchFragment.newInstance()
    }

    private val mypageFragment: MyPageFragment by lazy {
        MyPageFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bnMainBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, homeFragment).commit()
                }
                R.id.menu_chat -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, chatFragment).commit()
                }
                R.id.menu_post -> {

                }
                R.id.menu_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, searchFragment).commit()
                }
                R.id.menu_mypage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, mypageFragment).commit()
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment()).commit()
    }

}