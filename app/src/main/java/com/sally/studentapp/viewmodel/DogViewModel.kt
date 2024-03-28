package com.sally.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sally.studentapp.model.Dog

class DogViewModel(application: Application): AndroidViewModel(application) {
    val dogsLD = MutableLiveData<ArrayList<Dog>>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://icfubaya2023.com/dog"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Dog>>() { }.type
                val result = Gson().fromJson<List<Dog>>(it, sType)
                dogsLD.value = result as ArrayList<Dog>?
                Log.d("showvoley", it.toString())
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}