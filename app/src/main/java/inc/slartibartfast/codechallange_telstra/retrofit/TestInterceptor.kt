package inc.slartibartfast.codechallange_telstra.retrofit

import android.content.Context
import okhttp3.*

class TestInterceptor(private val context: Context) : Interceptor {
    private var refreshes = 0
    override fun intercept(chain: Interceptor.Chain): Response {

        val filename = when (refreshes++ % 5) {
            0 -> "success_refresh_00.json"
            1 -> "success_refresh_01.json"
            2 -> "success_refresh_02.json"
            3 -> "success_refresh_03.json"
            4 -> "fail.json"
            else -> "fail.json"
        }
        val responseString = context.assets.open(filename).bufferedReader().use { it.readText() }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    responseString.toByteArray()
                )
            )
            .build()
    }
}