package com.example.intermediate_baru.LOGIN

import android.content.Context
import androidx.lifecycle.*
import com.example.intermediate_baru.API.APIClient
import com.example.intermediate_baru.Model.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {

    private lateinit var prefer: SharePreference

    fun initPreferences(context: Context) {
        prefer = SharePreference(context)
    }

    private val _statuslogin: MutableLiveData<LoginResponse?> = MutableLiveData()
    val statuslogin: LiveData<LoginResponse?> get() = _statuslogin


            fun login(email: String, password: String) {
                val call = APIClient.getApiService().login(email, password)
                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        val loginresponse = response.body()
                        if (response.isSuccessful) {
                            if (loginresponse != null) {
                                saveUser(loginresponse.loginResult.name, loginresponse.loginResult.userId, loginresponse.loginResult.token)
                                _statuslogin.postValue(loginresponse)
                            }

                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    }

                })
            }
    private fun saveUser(name:String?, userid: String?, token:String?){
        viewModelScope.launch {
            if(::prefer.isInitialized) {
                prefer.berhasil_masuk = true
                prefer.token = token
            }
        }
    }
}