package com.tmdhoon.togather.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.DeleteUserDialogBinding
import com.tmdhoon.togather.databinding.FragmentChatBinding
import com.tmdhoon.togather.databinding.FragmentHomeBinding

class DeleteUserDialog : DialogFragment() {

    private lateinit var binding : DeleteUserDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.delete_user_dialog, container, false)

        return binding.root
    }
}