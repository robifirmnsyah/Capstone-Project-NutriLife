package com.example.intermediate_baru.REGISTER

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intermediate_baru.API.APIClient
import com.example.intermediate_baru.Model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {
    private val _statusregister: MutableLiveData<RegisterResponse?> = MutableLiveData()
    val statusregister: LiveData<RegisterResponse?> get() = _statusregister

    fun register(name:String, email: String, password:String){
        val call = APIClient.getApiService().register(name, email, password)
        call.enqueue(object :Callback<RegisterResponse>{
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                val regisresponse = response.body()
                if (response.isSuccessful) {
                    if(regisresponse != null) {
                        _statusregister.postValue(regisresponse)
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            }

        })
        }
    }