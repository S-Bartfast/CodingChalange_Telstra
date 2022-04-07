package inc.slartibartfast.codechallange_telstra.retrofit

import inc.slartibartfast.codechallange_telstra.CountryData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiDropbox {
    @GET("facts.json")
    fun getData() : Call<CountryData>

    companion object {

        var BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

        fun create() : ApiDropbox {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiDropbox::class.java)
        }
    }
}