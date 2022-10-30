package com.tmdhoon.togather.view.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.DialogDeleteUserBinding

class DeleteUserDialog : DialogFragment() {

    private lateinit var binding : DialogDeleteUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_delete_user, container, false)

        return binding.root
    }
}