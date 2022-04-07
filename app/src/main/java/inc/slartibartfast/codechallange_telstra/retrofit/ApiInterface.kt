package inc.slartibartfast.codechallange_telstra.retrofit

import android.content.Context
import inc.slartibartfast.codechallange_telstra.CountryData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("facts.json")
    fun getData() : Call<CountryData>

    companion object {

        val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

        // Client that retrieves data from Internet endpoint
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }

        // Client with Interceptor that returns contents of assets files.
        fun createWithInterceptor(context: Context) : ApiInterface {
            val client = OkHttpClient.Builder().addInterceptor(TestInterceptor(context)).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}