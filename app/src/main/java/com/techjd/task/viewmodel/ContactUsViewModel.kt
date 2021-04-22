package com.techjd.task.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techjd.task.api.swaggerhubApi
import com.techjd.task.model.SuccessObject.SuccessObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactUsViewModel @ViewModelInject constructor(private val swaggerhubApi: swaggerhubApi) :
    ViewModel() {

    private var message: MutableLiveData<String>

    init {
        message = MutableLiveData<String>()
    }


    fun submitContactUs(userDetails: String): MutableLiveData<String> {



        swaggerhubApi.contactUs(userDetails).enqueue(object : Callback<SuccessObject> {
            override fun onResponse(call: Call<SuccessObject>, response: Response<SuccessObject>) {
                when {
                    response.code().equals(200) -> {
                        message.postValue(response.body()!!.message)
                    }
                    response.code().equals(400) -> {
                        message.postValue("Bad Request")
                    }
                    response.code().equals(401) -> {
                        message.postValue("Unauthorised Request")
                    }
                    response.code().equals(500) -> {
                        message.postValue("Internal Server Error")
                    }
                }
            }

            override fun onFailure(call: Call<SuccessObject>, t: Throwable) {
                message.postValue("Some Error")
            }
        })

        return message
    }
}