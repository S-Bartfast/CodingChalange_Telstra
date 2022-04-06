package inc.slartibartfast.codechallange_telstra.viewmodel

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import inc.slartibartfast.codechallange_telstra.CountryData
import inc.slartibartfast.codechallange_telstra.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private var _liveCountryData: MutableLiveData<CountryData> = MutableLiveData()
    val liveCountryData = _liveCountryData

    fun makeApiCall() {
        val apiInterface = ApiInterface.create().getData()

        apiInterface.enqueue( object : Callback<CountryData> {
            override fun onResponse(call: Call<CountryData>, response: Response<CountryData>) {
                _liveCountryData.postValue(response.body())
            }

            override fun onFailure(call: Call<CountryData>, t: Throwable) {
                _liveCountryData.postValue(null)
            }
        })
    }

}