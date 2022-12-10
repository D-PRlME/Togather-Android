package com.tmdhoon.togather.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tmdhoon.togather.databinding.BottomSheetTagBinding
import com.tmdhoon.togather.remote.TagAdapter
import com.tmdhoon.togather.viewmodel.MainViewModel
import com.tmdhoon.togather.viewmodel.PostViewModel

private lateinit var bottomSheetDialog: BottomSheetDialog


fun initDialog(
    context: Context,
    view: View,
) {

    bottomSheetDialog = BottomSheetDialog(context).apply {
        setContentView(view)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        show()
    }
}

fun createTagFragment(
    context : Context,
    mainViewModel : MainViewModel,
    viewLifecycleOwner: LifecycleOwner,
    postViewModel : PostViewModel,
){
    val binding by lazy {
        BottomSheetTagBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    initDialog(
        context = context,
        view = binding.root,
    )

    mainViewModel.tag()

    mainViewModel.tagResponse.observe(viewLifecycleOwner) {
        when (it.code()) {
            200 -> {
                binding.rvTagRecyclerView.run {
                    adapter = TagAdapter(it.body()!!.tags, postViewModel)
                    layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }
}

fun hideKeyBoard(
    context : Context,
    view : View,
){
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
