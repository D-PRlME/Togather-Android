package com.tmdhoon.togather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.ActivityMainBinding
import com.tmdhoon.togather.view.fragment.*

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

    private val myInfoFragment: MyInfoFragment by lazy {
        MyInfoFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
        initBottomNavigationPost()

    }

    private fun initBottomNavigationPost() {
        binding.bnMainBottomNavigationPost.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_post -> {
                    val bottomSheetFragment = PostFragment()
                    BottomSheetBehavior.STATE_EXPANDED
                    bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
                }
            }
            true
        }
    }

    private fun initBottomNavigation() {
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
                        .replace(R.id.frameLayout, myInfoFragment).commit()
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