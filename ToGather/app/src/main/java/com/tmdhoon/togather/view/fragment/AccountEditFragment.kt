package com.tmdhoon.togather.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tmdhoon.togather.R
import com.tmdhoon.togather.databinding.FragmentAccountEditBinding
import com.tmdhoon.togather.util.positionNameList
import com.tmdhoon.togather.util.printToast
import com.tmdhoon.togather.util.selectedList
import com.tmdhoon.togather.viewmodel.MyInfoViewModel

class AccountEditFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentAccountEditBinding

    private val myInfoViewModel : MyInfoViewModel by lazy {
        MyInfoViewModel()
    }

    private lateinit var positionButtonList : ArrayList<View>
    private lateinit var positionList : ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initDataBinding(inflater, container)
        initRequest()
        initSaveButton()
        initCloseButton()
        initObserveEditAccountResponse()
        initPositionButton()

        positionButtonList = arrayListOf(
            binding.btAndroid,
            binding.btIOS,
            binding.btPm,
            binding.btFront,
            binding.btDesigner,
            binding.btBack,
        )

        positionList = ArrayList()

        return binding.root
    }

    private fun initPositionButton() {
        binding.run {
            btAndroid.setOnClickListener { setPositionButtonBackground(0) }
            btIOS.setOnClickListener { setPositionButtonBackground(1) }
            btPm.setOnClickListener { setPositionButtonBackground(2) }
            btFront.setOnClickListener { setPositionButtonBackground(3) }
            btDesigner.setOnClickListener { setPositionButtonBackground(4) }
            btBack.setOnClickListener { setPositionButtonBackground(5) }
        }
    }

    private fun setPositionButtonBackground(num : Int){
        if(selectedList[num]){
            positionButtonList[num].setBackgroundResource(R.drawable.button_white_short)
            positionList.remove(positionNameList[num])
            selectedList[num] = false
        }else{
            positionButtonList[num].setBackgroundResource(R.drawable.button_yellow_short)
            positionList.add(positionNameList[num].uppercase())
            selectedList[num] = true
        }
    }

    private fun initSaveButton() {
        binding.btAccountEditSave.setOnClickListener {
            val name = binding.etAccountEditName.text.toString()
            val url = ""
            val introduce = binding.etAccountEditIntroduce.text.toString()
            if(name.isNotEmpty() && introduce.isNotEmpty()){
                Log.d("TEST", positionList.toString())
                myInfoViewModel.editAccount(name, url, introduce, positionList)
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account_edit, container, false)
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
        myInfoViewModel.accountEditResponse.observe(this) {
            when(it.code()){
                204->{
                    printToast(this.context, "정보가 성공적으로 변경되었습니다")
                    initRequest()
                }
                400 -> printToast(this.context, "항목을 확인해주세요!")
            }
        }
    }
}