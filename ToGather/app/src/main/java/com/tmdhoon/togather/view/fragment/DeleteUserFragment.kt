package com.tmdhoon.togather.view.fragment

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentDeleteUserBinding
import com.tmdhoon.togather.viewmodel.MyInfoViewModel

class DeleteUserFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentDeleteUserBinding

    private val myInfoViewModel : MyInfoViewModel by lazy {
        MyInfoViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_delete_user, container, false)
        binding.myInfoViewModel = myInfoViewModel
        binding.lifecycleOwner = this
        binding.deleteUserFragment = this
        observePwEdittext()
        myInfoViewModel.myInfo()

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }

    fun deleteUser(){
        val pw = binding.etDeleteUserPw.text.toString()
        DeleteUserDialog().show(parentFragmentManager, DeleteUserDialog().tag)
    }

    private fun observePwEdittext(){
        binding.etDeleteUserPw.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val pw = binding.etDeleteUserPw.text
                if(pw.isNotEmpty()){
                    binding.btDeleteUserDeleteUser.setBackgroundResource(R.drawable.button_red)
                    binding.btDeleteUserDeleteUser.setTextColor(Color.BLACK)
                }else{
                    binding.btDeleteUserDeleteUser.setBackgroundResource(R.drawable.button_white)
                    binding.btDeleteUserDeleteUser.setTextColor(ContextCompat.getColor(this@DeleteUserFragment.requireContext(), R.color.all_background_focusOut))
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}