package com.tmdhoon.togather.view

import android.animation.ObjectAnimator
import android.app.ProgressDialog.show
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.FrameLayout
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
            when(it.itemId){
                R.id.menu_post -> PostFragment().show(supportFragmentManager, PostFragment().tag)
            }
            true
        }
    }

    private fun initBottomNavigation() {
        binding.bnMainBottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, HomeFragment()).commitAllowingStateLoss()

                R.id.menu_chat -> supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, ChatFragment()).commitAllowingStateLoss()

                R.id.menu_search -> supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, SearchFragment()).commitAllowingStateLoss()

                R.id.menu_mypage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, MyInfoFragment()).commitAllowingStateLoss()
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, HomeFragment()).commitAllowingStateLoss()
    }

}