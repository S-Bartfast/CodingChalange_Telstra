package inc.slartibartfast.codechallange_telstra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = ApiInterface.create().getData()

        apiInterface.enqueue( object : Callback<CountryData> {
            override fun onResponse(call: Call<CountryData>, response: Response<CountryData>) {

                val titles = StringBuilder()
                response.body()?.rows?.forEach {
                    titles.appendLine(it.title)
                }

                val textView = findViewById<TextView>(R.id.the_text_view)
                textView.text = titles
            }

            override fun onFailure(call: Call<CountryData>, t: Throwable) {

            }
        })
    }
}