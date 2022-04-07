package inc.slartibartfast.codechallange_telstra.retrofit

import inc.slartibartfast.codechallange_telstra.CountryData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiFail {
    @GET("NoSuchFile.json")
    fun getData() : Call<CountryData>

    companion object {

        val BASE_URL = "https://NoSuchUrl/"

        fun create() : ApiDropbox {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiDropbox::class.java)
        }
    }
}
