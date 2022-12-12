package com.tmdhoon.togather.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import com.tmdhoon.togather.repository.PostRepository
import com.tmdhoon.togather.util.getPref
import com.tmdhoon.togather.util.initPref
import com.tmdhoon.togather.util.putPref
import org.json.JSONArray
import retrofit2.Response

class PostViewModel(
    private val pref : SharedPreferences,
) : ViewModel() {

    val postResponse: MutableLiveData<Response<Void>> = MutableLiveData()
    val editResponse: MutableLiveData<Response<Void>> = MutableLiveData()
    val onClick : MutableLiveData<Boolean> = MutableLiveData()

    private val tagList : ArrayList<String> by lazy {
        ArrayList()
    }

    private val postRepository by lazy {
        PostRepository(this)
    }

    fun post(
        title: String,
        content: String,
    ) {
        Log.d("TEST", "getTag ${getTag()}")
        postRepository.post(
            title = title,
            tags = getTag(),
            content = content,
        )
    }

    fun editPost(
        title: String,
        content: String,
        postId: Int,
        tagList: ArrayList<String>
    ) {
        postRepository.editPost(
            title = title,
            tags = tagList,
            content = content,
            postId = postId.toLong(),
        )
    }


    fun addTag(tag : String){
        tagList.add(tag)
    }

    fun removeTag(tag : String){
        tagList.remove(tag)
    }

    fun getTag() : ArrayList<String> {
        return tagList
    }

    fun saveTag(tag : String?) {
        putPref(pref.edit(), "tag", tag)
    }

    fun roadTag() : String =
        getPref(pref, "tag", "") as String

}