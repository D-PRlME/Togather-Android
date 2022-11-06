package com.tmdhoon.togather.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.BottomSheetAccountEditBinding
import com.tmdhoon.togather.util.printToast
import com.tmdhoon.togather.viewmodel.MyInfoViewModel

class AccountEditFragment : BottomSheetDialogFragment() {

    private lateinit var binding : BottomSheetAccountEditBinding

    private val myInfoViewModel : MyInfoViewModel by lazy {
        MyInfoViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initDataBinding(inflater, container)
        initRequest()
        initSaveButton()
        initCloseButton()
        initObserveEditAccountResponse()

        return binding.root
    }

    private fun initSaveButton() {
        binding.btAccountEditSave.setOnClickListener {
            val name = binding.etAccountEditName.text.toString()
            val url = ""
            val introduce = binding.etAccountEditIntroduce.text.toString()
            if(!name.isBlank()){
                myInfoViewModel.editAccount(name, url, introduce, listOf())
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }

    private fun initRequest() {
        myInfoViewModel.myInfo()
    }

    private fun initDataBinding(inflater : LayoutInflater, container: ViewGroup?){
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_account_edit, container, false)
        binding.user = myInfoViewModel
        binding.accountEditFragment = this
        binding.lifecycleOwner = this
    }

    private fun initCloseButton() {
        binding.imgPostClose.setOnClickListener {
            dismiss()
        }
    }

    fun deleteUser(){
        DeleteUserFragment().show(parentFragmentManager, DeleteUserFragment().tag)
    }

    private fun initObserveEditAccountResponse(){
        myInfoViewModel.accountEditResponse.observe(this, Observer {
            when(it.code()){
                204->{
                    printToast(this.context, "정보가 성공적으로 변경되었습니다")
                    initRequest()
                }
                400 -> printToast(this.context, "항목을 확인해주세요!")
            }
        })
    }
}