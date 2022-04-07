package inc.slartibartfast.codechallange_telstra.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import inc.slartibartfast.codechallange_telstra.CountryData
import inc.slartibartfast.codechallange_telstra.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private var _liveCountryData: MutableLiveData<CountryData> = MutableLiveData()
    val liveCountryData = _liveCountryData
    lateinit var apiInterface: ApiInterface

    fun makeApiCall(swipeContainer: SwipeRefreshLayout) {
        swipeContainer.isRefreshing = true

        apiInterface.getData().enqueue( object : Callback<CountryData> {
            override fun onResponse(call: Call<CountryData>, response: Response<CountryData>) {
                _liveCountryData.postValue(response.body())
                swipeContainer.isRefreshing = false
            }

            override fun onFailure(call: Call<CountryData>, t: Throwable) {
                _liveCountryData.postValue(CountryData("Error", listOf()))
                swipeContainer.isRefreshing = false

                Log.e(TAG, "Failure attempting to get data from server", t)
            }
        })
    }

    companion object {
        const val TAG = "MainActivityViewModel"
    }
}